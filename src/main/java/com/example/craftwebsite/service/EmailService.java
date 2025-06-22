// package com.example.craftwebsite.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.stereotype.Service;

// @Service
// public class EmailService {

//     @Autowired
//     private JavaMailSender mailSender;

//     @Value("${spring.mail.username}")
//     private String fromEmail;

//     @Value("${app.frontend.reset-url}")
//     private String resetUrl;

//     public void sendResetEmail(String toEmail, String token) {
//         String subject = "Password Reset Request";
//         String link = resetUrl + "?token=" + token;
//         String body = "Click the link to reset your password: " + link;

//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo(toEmail);
//         message.setSubject(subject);
//         message.setText(body);
//         message.setFrom(fromEmail); // use configured sender

//         try {
//             System.out.println("Sending email to: " + toEmail); // debug print
//             mailSender.send(message);
//             System.out.println("Email sent successfully");
//         } catch (Exception e) {
//             System.err.println("Failed to send email: " + e.getMessage());
//             e.printStackTrace();
//         }
//     }
// }
