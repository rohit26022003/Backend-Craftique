package com.example.craftwebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    // Thread-safe OTP store
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    // OTP expiry tracking
    private final Map<String, Long> otpExpiry = new ConcurrentHashMap<>();

    // OTP validity: 5 minutes (in milliseconds)
    private static final long OTP_EXPIRY_MS = 5 * 60 * 1000;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends a 6-digit OTP to the given email address and stores it with expiration.
     */
    public void sendOtp(String email) {
        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // Normalize email
        String normalizedEmail = email.trim().toLowerCase();

        // Store OTP and expiration time
        otpStore.put(normalizedEmail, otp);
        otpExpiry.put(normalizedEmail, System.currentTimeMillis() + OTP_EXPIRY_MS);

        // Compose email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(normalizedEmail);
        message.setSubject("Your Password Reset OTP");
        message.setText("Your OTP is: " + otp + "\nThis OTP is valid for 5 minutes.");

        // Send email
        mailSender.send(message);

        // Optional: Log the OTP (for dev/testing only, remove in production)
        System.out.println("Sent OTP to " + normalizedEmail + ": " + otp);
    }

    /**
     * Verifies whether the provided OTP is correct and not expired.
     *
     * @param email the email used to request OTP
     * @param otp   the OTP entered by the user
     * @return true if valid and not expired, false otherwise
     */
    public boolean verifyOtp(String email, String otp) {
        String normalizedEmail = email.trim().toLowerCase();

        // Check if OTP exists
        if (!otpStore.containsKey(normalizedEmail)) return false;

        // Check if expired
        long expiryTime = otpExpiry.getOrDefault(normalizedEmail, 0L);
        if (System.currentTimeMillis() > expiryTime) {
            otpStore.remove(normalizedEmail);
            otpExpiry.remove(normalizedEmail);
            return false;
        }

        // Check if OTP matches
        boolean isValid = otpStore.get(normalizedEmail).equals(otp);

        // Invalidate OTP regardless of match (one-time use)
        otpStore.remove(normalizedEmail);
        otpExpiry.remove(normalizedEmail);

        return isValid;
    }
}
