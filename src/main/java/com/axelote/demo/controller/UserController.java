package com.axelote.demo.controller;

import com.axelote.demo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Value("${credentials.username}")
    private String username;

    @Value("${credentials.password}")
    private String password;

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> userAuthentication(@RequestBody User user) {
        if (!user.username.equals(username) || !user.password.equals(password)) {
            return ResponseEntity.ok("BAD_USER_CREDENTIALS");
        }
        return ResponseEntity.ok("OK");
    }
}