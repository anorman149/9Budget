package com.ninebudget.service;

import com.ninebudget.model.dto.ApplicationUserDto;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Service for sending emails.
 */
@Service
@PropertySource("classpath:mail.properties")
public class MailService {
    private final Logger log = LoggerFactory.getLogger(MailService.class);

    @Value("#{'${from}'}")
    private String from;

    @Value("#{'${host}'}")
    private String host;

    @Value("#{'${port}'}")
    private String port;

    @Value("#{'${username}'}")
    private String username;

    @Value("#{'${password}'}")
    private String password;

    public void sendEmail(String to, String subject, String content) {
        log.debug("Send email to '{}' with subject '{}' and content={}", to, subject, content);

        // Setup mail server
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Prepare message
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));

            message.setSubject(subject);
            message.setContent(content, "text/html");

//            Transport.send(message);
            log.debug("Sent email to User '{}'", to);
        }  catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }

    public void sendEmailFromTemplate(ApplicationUserDto user, String templateName, String subject) throws IOException {
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getId());
            return;
        }

        String content = IOUtils.toString(getClass().getResourceAsStream(templateName), StandardCharsets.UTF_8);
        content = content.replaceAll("[name]", user.getFirstName());
        content = content.replaceAll("[url]", user.getFirstName());

        sendEmail(user.getEmail(), subject, content);
    }

    public void sendActivationEmail(ApplicationUserDto user) throws IOException {
        log.debug("Sending activation email to '{}'", user.getEmail());

        sendEmailFromTemplate(user, "/templates/mail/activationEmail.html", "9Budget Account Activation");
    }

    public void sendPasswordResetMail(ApplicationUserDto user) throws IOException {
        log.debug("Sending password reset email to '{}'", user.getEmail());

        sendEmailFromTemplate(user, "/templates/mail/passwordResetEmail", "9Budget Password Reset");
    }
}