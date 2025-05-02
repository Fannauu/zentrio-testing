package org.example.zentriotesting.model.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {
    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email better follow its format!")
    private String email;
    @NotBlank(message = "New Password cannot be blank!")
    private String newPassword;
    @NotBlank(message = "Confirm Password cannot be blank!")
    private String confirmNewPassword;

}
