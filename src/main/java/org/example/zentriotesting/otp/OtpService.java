package org.example.zentriotesting.otp;

import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class OtpService {

    private final Map<String, OtpEntry> optStorage = new ConcurrentHashMap<>();

    public void sendOtp(String email, String otp) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(3);
        System.out.println("otp will expire in " + expiryTime);
        optStorage.put(email, new OtpEntry(otp, expiryTime));
    }

    public OtpEntry getOtp(String email) {
        OtpEntry otpEntry = optStorage.get(email);
        if (otpEntry != null && LocalDateTime.now().isAfter(otpEntry.getExpiryTime())) {
            // OTP has expired, remove it from storage
            clearOtp(email);
            return null; // Return null if OTP has expired
        }
        return otpEntry; // Return OTP entry if valid
    }

    public void clearOtp(String email) {
        optStorage.remove(email);
    }


}
