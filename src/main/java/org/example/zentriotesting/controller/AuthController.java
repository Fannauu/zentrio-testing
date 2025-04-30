package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.jwt.JwtService;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.request.AppUserRequest;
import org.example.zentriotesting.model.entity.request.AuthRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.model.entity.response.AppUserDTO;
import org.example.zentriotesting.model.entity.response.TokenResponse;
import org.example.zentriotesting.otp.EmailService;
import org.example.zentriotesting.otp.OtpEntry;
import org.example.zentriotesting.otp.OtpService;
import org.example.zentriotesting.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller")
public class AuthController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
//    adding forgot Boolean isForgot for new flow with reset password
    public ResponseEntity<?> register(@RequestBody AppUserRequest request, @RequestParam String provider) throws Exception {

        if (!provider.equals("credential") && !provider.equals("google")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.<String>builder()
                            .success(false)
                            .message("Invalid registration type. Must be 'credential' or 'google'.")
                            .status(HttpStatus.BAD_REQUEST)
                            .timestamp(LocalDateTime.now())
                            .build());
        }

        System.out.println("request: " + request);

        AppUser existingUser = appUserService.getUserByEmail(request.getEmail());
        if (existingUser != null) {

            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.<String>builder()
                            .success(false)
                            .message("You are already registered.")
                            .status(HttpStatus.CONFLICT)
                            .timestamp(LocalDateTime.now())
                            .build());
        }


//        AppUserDTO registerUser = appUserService.register(request);
        AppUserDTO registerUser;

        if (provider.equals("credential")) {
            registerUser = appUserService.register(request);

            if (registerUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.<AppUserDTO>builder()
                                .success(false)
                                .message("Failed to register the user. Possible duplicate email or validation error.")
                                .status(HttpStatus.BAD_REQUEST)
                                .timestamp(LocalDateTime.now())
                                .build());
            }
            // Send OTP
            String otpRandom = String.valueOf(new Random().nextInt(900000) + 100000);
            otpService.sendOtp(otpRandom, registerUser.getEmail());
            emailService.sendOtpEmail(registerUser.getEmail(), otpRandom);
        } else {
            registerUser = appUserService.registerGoogleUser(request);
            if (registerUser == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.<AppUserDTO>builder()
                                .success(false)
                                .message("Google registration failed.")
                                .status(HttpStatus.BAD_REQUEST)
                                .timestamp(LocalDateTime.now())
                                .build());
            }
        }

        // Success response
        ApiResponse<AppUserDTO> apiResponse = ApiResponse.<AppUserDTO>builder()
                .success(true)
                .message("Register successfully")
                .payload(registerUser)
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(201).body(apiResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());

        AppUser user = appUserService.getUserByEmail(request.getEmail());

        if (!user.getIsVerified()) {
            throw new BadCredentialsException("Your gmail " + request.getEmail() + " are not verified");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        if (!user.getEmail().equals(request.getEmail())) {
            throw new BadCredentialsException("Wrong email");
        }

        final String token = jwtService.generateToken(userDetails);
        TokenResponse tokenResponse = new TokenResponse(token);

        return ResponseEntity.ok(ApiResponse.<TokenResponse>builder()
                .success(true)
                .message("login successfully here is your token")
                .status(HttpStatus.CREATED)
                .payload(tokenResponse)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@RequestParam String email, @RequestParam String otp) throws Exception {
        OtpEntry otpEntry = otpService.getOtp(email);

        if (otpEntry == null) {
            throw new BadCredentialsException("OTP not found or expired.");
        }

        if (LocalDateTime.now().isAfter(otpEntry.getExpiryTime())) {
            otpService.clearOtp(email);
            throw new BadCredentialsException("OTP is expired.");
        }

        if (!otpEntry.getOtp().equals(otp)) {
            throw new BadCredentialsException("Invalid otp please try again later.");
        }

        AppUser user = appUserService.getUserByEmail(email);

        if (user == null) {
            throw new BadCredentialsException("User email not found.");
        }

        if (user.getIsVerified()) {
            throw new BadCredentialsException("User already is verified");
        }
        user.setIsVerified(true);
        appUserService.save(user);
        otpService.clearOtp(email);


        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .success(true)
                .message("verified successfully ")
                .payload("...")
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @Operation(summary = "Resend otp")
    @PostMapping("/resend")
    public ResponseEntity<ApiResponse<String>> resend(@RequestParam String email) throws MessagingException {

        UserDetails userDetails = appUserService.loadUserByUsername(email);

        if (userDetails == null) {
            ApiResponse<String> apiResendResponse = ApiResponse.<String>builder()
                    .success(false)
                    .message("Your email not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(apiResendResponse);
        }
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); //  6-digit OTP
        otpService.sendOtp(email, otp);
        emailService.sendOtpEmail(email, otp);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .success(true)
                .message("Verification OTP successfully resent to your email.")
                .payload("...")
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}

