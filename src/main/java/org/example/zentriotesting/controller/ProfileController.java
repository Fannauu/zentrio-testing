package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.zentriotesting.model.entity.request.ProfileRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.model.entity.response.AppUserDTO;
import org.example.zentriotesting.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("api/v1/profiles")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProfileController {

    private final AppUserService appUserService;

    @GetMapping()
    public ResponseEntity<ApiResponse<AppUserDTO>> getProfile() {
        ApiResponse<AppUserDTO> apiResponse = ApiResponse.<AppUserDTO>builder()
                .success(true)
                .message("Get profile successfully")
                .payload(appUserService.getAuthenticatedUser())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<AppUserDTO>> updateUserProfile(ProfileRequest profileRequest) {
        ApiResponse<AppUserDTO> apiResponse = ApiResponse.<AppUserDTO>builder()
                .success(true)
                .message("Updated profile successfully")
                .payload(appUserService.updateUserProfile(profileRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
