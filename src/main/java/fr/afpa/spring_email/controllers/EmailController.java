package fr.afpa.spring_email.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.spring_email.services.impl.EmailServiceImpl;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    public SimpleMailMessage template;

    @Autowired
    private EmailServiceImpl emailService;

    // http://localhost:8008/email/send-template?to=lucie.fiorrito@proton.me&name=Lucie
    @GetMapping("/send-template")
    public String sendTemplateEmail(@RequestParam String to, @RequestParam String name) {
        String subject = "Bienvenue !";
        String text = String.format(template.getText(), name);

        emailService.sendSimpleMessage(to, subject, text);
        return "Email envoyé à " + to;
    }

    @GetMapping("/send-simple-attachment")
    public String sendEmailWithAttachment() throws MessagingException, IOException {
        String to = "lucie.fiorrito@proton.me";
        String subject = "Subject Here";
        String text = String.format(template.getText(), "coucou");
        // String pathToAttachment = "../../resources/txt/file.txt";
        File file = new ClassPathResource("txt/file.txt").getFile();

        emailService.sendMessageWithAttachment(to, subject, text, file.getAbsolutePath());

        return "Email avec pièce jointe envoyé !";
    }

    @GetMapping("/send-inputstream-attachment")
    public String sendEmailWithInputStreamAttachment() {
        String to = "lucie.fiorrito@proton.me";
        String subject = "Subject Here";
        String body = "Body of the email";
        String attachmentName = "attachment.txt";

        byte[] attachmentData = "Hello World".getBytes(StandardCharsets.UTF_8);

        emailService.sendMessageWithInputStreamAttachment(to, subject, body, attachmentName, attachmentData);

        return "Email avec pièce jointe envoyé !";
    }
}
