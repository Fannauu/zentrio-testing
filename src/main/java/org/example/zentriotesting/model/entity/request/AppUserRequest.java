package org.example.zentriotesting.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.zentriotesting.model.entity.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {
    private String username;
    private Gender gender;
    private String email;
    private String password;
    private String profileImage;
}
