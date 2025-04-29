package org.example.zentriotesting.otp;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;


    public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        try {
            // Prepare Thymeleaf context with the OTP
            Context context = new Context();
            context.setVariable("otp", otp);

            // Process the template to get the email content
            String html = templateEngine.process("verification-email", context);

            // Create the email message
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Your OTP Code From Zentrio");
            helper.setText(html, true); // true means the content is HTML

            // Send the email
            mailSender.send(message);
        } catch (MessagingException e) {
            final Logger logger = LoggerFactory.getLogger(EmailService.class);
            logger.error("Failed to send OTP email to {}", toEmail, e);

            // Log error with message details
//            e.printStackTrace();
//            throw e; // Re-throwing exception for proper error handling
        }
    }

}
