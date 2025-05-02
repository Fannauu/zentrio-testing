package org.example.zentriotesting.model.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Username cannot be blank!")
    private String username;
    private Gender gender;
    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email better follow its format!")
    private String email;
    @NotBlank(message = "Password cannot be blank!")
    private String password;
    private String profileImage;
}
