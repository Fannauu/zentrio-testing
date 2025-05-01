package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.request.AppUserRequest;
import org.example.zentriotesting.model.entity.request.ProfileRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.model.entity.response.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AppUserService extends UserDetailsService {
    AppUserDTO register(AppUserRequest request);

    AppUser getUserByEmail(String email);

    AppUserDTO getAuthenticatedUser();

    AppUserDTO updateUserProfile(ProfileRequest request);

    void save(AppUser user);

    AppUserDTO registerGoogleUser(AppUserRequest request);
    void saveReset(AppUser user);

    AppUser reSetPassword(String email, String newPassword);

    UUID getCurrentUserId();
}
