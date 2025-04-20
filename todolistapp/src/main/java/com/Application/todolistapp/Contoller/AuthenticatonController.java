package com.Application.todolistapp.Contoller;

import com.Application.todolistapp.RequestDTO.UserAuthreqDTO;
import com.Application.todolistapp.RequestDTO.UserReqDTO;
import com.Application.todolistapp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticatonController {

    @Autowired
   public AuthenticationService authenticationservice;

   @Autowired
   PasswordEncoder passwordEncoder;

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@RequestBody UserAuthreqDTO userauthreqdto){
        userauthreqdto.setPassword(passwordEncoder.encode(userauthreqdto.getPassword()));
        authenticationservice.saveUser(userauthreqdto);
        return ResponseEntity.ok("USer registered successfully");
    }


}
