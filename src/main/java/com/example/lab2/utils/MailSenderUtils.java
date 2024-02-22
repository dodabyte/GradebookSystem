package com.example.lab2.utils;

import com.example.lab2.AppManager;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderUtils {
    private static String debugMail = "maria13sivi@yandex.ru";

    public static void sendPassword(String toEmail, String newPassword) {
        String username = "mayahimayahamayahaha";
        String password = "bdpr tqpn iakd iubc";
        String subject = "Учетные данные для сервиса ведения зачетных книжек";
        String text = "Здравствуйте!\n\n" +
                      "Ваши данные для входа в учетную запись в сервисе ведения зачетных книжек:\n" +
                      "Электронная почта: " + toEmail + "\n" +
                      "Пароль: " + newPassword + "\n\n\n" +
                      "Письмо сгенерировано автоматически. Не отвечайте на него.";

        if (AppManager.getDebugModeEnable())
            send(username, password, subject, text, debugMail);
        else
            send(username, password, subject, text, toEmail);
    }

    private static void send(String username, String password, String subject, String text, String toEmail){
        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
