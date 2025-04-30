package org.example.zentriotesting.model.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {

    private String email;
    private String newPassword;
    private String confirmNewPassword;

}
