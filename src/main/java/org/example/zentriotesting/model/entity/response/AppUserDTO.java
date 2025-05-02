package org.example.zentriotesting.model.entity.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.zentriotesting.model.entity.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private UUID userId;
    private String username;
    private Gender gender;
    private String email;
    private String profileImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public AppUserDTO(UUID userId, String username,Gender gender, String email, String profileImage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.profileImage = profileImage;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public AppUserDTO(UUID userId, String identifier) {
        this.userId = userId;
        this.email = identifier;
    }
}
