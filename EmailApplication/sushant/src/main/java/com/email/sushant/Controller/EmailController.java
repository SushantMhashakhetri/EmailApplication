package com.email.sushant.Controller;

import com.email.sushant.DTO.EmailRequest;
import com.email.sushant.DTO.EmailResponse;

import com.email.sushant.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Properties;

@RestController
@CrossOrigin("*")
@RequestMapping("api/email")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/send")
    public ResponseEntity<EmailResponse> sendMail(@RequestBody EmailRequest emailRequest){

//        environment("my.app.name", name);
        emailService.sendWithHtml(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getMessage());
        return ResponseEntity.ok(EmailResponse.builder().msg("Email Sent Successfully !!").status(HttpStatus.OK).build());
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<EmailResponse> sendMailWithFile(@RequestPart EmailRequest emailRequest, @RequestPart MultipartFile file) throws IOException{
        emailService.sendWithFileWithStream(emailRequest.getTo(),emailRequest.getSubject(),emailRequest.getMessage(),file.getInputStream());
        return ResponseEntity.ok(EmailResponse.builder().msg("Email Sent Successfully !!").status(HttpStatus.OK).build());
    }
}












