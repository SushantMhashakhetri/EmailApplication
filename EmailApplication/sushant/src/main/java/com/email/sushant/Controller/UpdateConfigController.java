package com.email.sushant.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class UpdateConfigController {

    @Value("${spring.mail.username}")
    private String userName;

    @Value("${spring.mail.password}")
    private String password;

    @GetMapping("getuserpass")
    public ResponseEntity<Map<String,String>> getUserNamePassword(){
        Map<String,String> response = new HashMap<>();
        response.put("userName",userName);
        response.put("password",password);
        return ResponseEntity.ok().body(response);
    }
}
