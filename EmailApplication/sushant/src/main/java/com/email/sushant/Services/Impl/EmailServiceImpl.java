package com.email.sushant.Services.Impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.email.sushant.Services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    EmailServiceImpl(JavaMailSender javaMailSender){
     this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String to, String subject, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("gunjanmhashakhetri@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);

        javaMailSender.send(simpleMailMessage);
        LOGGER.info("Mail sent");

    }

    @Override
    public void send(String[] to, String subject, String msg) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("gunjanmhashakhetri@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msg);

        javaMailSender.send(simpleMailMessage);
        LOGGER.info("Mail sent");
    }

    @Override
    public void sendWithHtml(String to, String subject, String html) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(html,true);
        javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWithFile(String to, String subject, String msg, File file) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            FileSystemResource fsr = new FileSystemResource(file);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("gunjanmhashakhetri@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(msg);
            mimeMessageHelper.addAttachment(fsr.getFilename(),file);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWithFileWithStream(String to, String subject, String html, InputStream is) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("gunjanmhashakhetri@gmail.com");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(html,true);
            File file =new File("src/main/resources/email/test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fsr = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fsr.getFilename(),file);
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
