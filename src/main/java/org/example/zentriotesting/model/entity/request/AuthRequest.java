package org.example.zentriotesting.model.entity.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Identifier cannot be blank!")
    private String identifier;
    @NotBlank(message = "Password cannot be blank!")
    private String password;
}
