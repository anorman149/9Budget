package com.ninebudget.service;

import com.ninebudget.model.dto.ApplicationUserDto;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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

    @Value("#{'${mail.username}'}")
    private String mailUsername;

    @Value("#{'${mail.password}'}")
    private String mailPassword;

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
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));

            message.setSubject(subject);
            message.setContent(content, "text/html");

            Transport.send(message);
            log.debug("Sent email to User '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }

    public void sendEmailFromTemplate(ApplicationUserDto user, String templateName, String subject, URI uri) throws MailException {
        String content;
        try {
            if (user.getEmail() == null) {
                log.warn("Email doesn't exist for user '{}'", user.getId());
                throw new Exception("Email doesn't exist for user");
            }

            content = IOUtils.toString(getClass().getResourceAsStream(templateName), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new com.ninebudget.model.MailException("Mail could not be delivered: " + e.getMessage(), e);
        }

        Map<String, String> values = new HashMap<>();
        values.put("name", user.getFirstName());
        values.put("url", uri.toString());

        String message = StringSubstitutor.replace(content, values, "{", "}");

        sendEmail(user.getEmail(), subject, message);
    }

    public void sendActivationEmail(ApplicationUserDto user) throws MailException {
        log.debug("Sending activation email to '{}'", user.getEmail());

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        String uriString = builder.toUriString();
        uriString = uriString.replace("mail", "users/activate/" + user.getActivationKey());
        URI newUri = URI.create(uriString);

        sendEmailFromTemplate(user, "/templates/mail/activationEmail.html", "9Budget Account Activation", newUri);
    }

    public void sendPasswordResetMail(ApplicationUserDto user) throws MailException {
        log.debug("Sending password reset email to '{}'", user.getEmail());

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        String uriString = builder.toUriString();
        uriString = uriString.replace("api/v1/users/password/reset", "resetpassword/" + user.getResetKey());
        URI newUri = URI.create(uriString);

        sendEmailFromTemplate(user, "/templates/mail/passwordResetEmail.html", "9Budget Password Reset Request", newUri);
    }

    public void sendCompleteResetMail(ApplicationUserDto user) throws MailException {
        log.debug("Sending password complete reset email to '{}'", user.getEmail());

        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();

        sendEmailFromTemplate(user, "/templates/mail/completePasswordResetEmail.html", "9Budget User Password Reset", builder.build().toUri());
    }
}
