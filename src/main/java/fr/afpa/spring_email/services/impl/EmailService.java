package fr.afpa.spring_email.services.impl;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
            throws MessagingException;

    public void sendMessageWithInputStreamAttachment(String to, String subject, String text, String attachmentName,
            byte[] attachmentData);
}
