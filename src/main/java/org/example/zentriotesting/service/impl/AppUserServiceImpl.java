package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Gender;
import org.example.zentriotesting.model.entity.request.AppUserRequest;
import org.example.zentriotesting.model.entity.request.ProfileRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.model.entity.response.AppUserDTO;
import org.example.zentriotesting.otp.OtpEntry;
import org.example.zentriotesting.repository.AppUserRepository;
import org.example.zentriotesting.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.getUserByEmail(email);
    }

    @Override
    public AppUserDTO register(AppUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getGender().equals(Gender.MALE)) {
            request.setProfileImage("https://i.pinimg.com/736x/3e/9f/08/3e9f085ce52735854f9f2d4742f86659.jpg");
        } else {
            request.setProfileImage("https://i.pinimg.com/736x/d0/7b/a6/d07ba6dcf05fa86c0a61855bc722cb7a.jpg");
        }
        AppUser appUser = appUserRepository.register(request);
        return appUser.toAppUserDTO(appUser);
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return appUserRepository.getUserByEmail(email);
    }

    @Override
    public AppUserDTO getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.getUserByEmail(authentication.getName());
        AppUserDTO appUserDTO = appUser.toAppUserDTO(appUser);
        return appUserDTO;
    }

    @Override
    public AppUserDTO updateUserProfile(ProfileRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.updateUserProfile(authentication.getName(),request);
        AppUserDTO appUserDTO = appUser.toAppUserDTO(appUser);
        return appUserDTO;
    }

    @Override
    public void save(AppUser user) {
        appUserRepository.save(user);
    }





}

