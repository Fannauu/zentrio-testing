package org.example.zentriotesting.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntry {
    private String otp;
    private LocalDateTime expiryTime;
}
