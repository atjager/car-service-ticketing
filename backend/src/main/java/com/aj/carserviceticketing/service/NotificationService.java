package com.aj.carserviceticketing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @Value("${email.address}")
    private final String username;

    @Value("${email.password}")
    private final String password;

    @Value("${mail.smtp.host}")
    private final String host;

    @Value("${mail.smtp.port}")
    private final String port;

    @Value("${mail.smtp.auth}")
    private final String auth;

    @Value("${mail.smtp.starttls.enable}")
    private final String starttls;

    public void send(String emailAddress, String subject, String msg) {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", auth);
        prop.put("mail.smtp.starttls.enable", starttls); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
