package com.Application.todolistapp.Contoller;

import com.Application.todolistapp.RequestDTO.UserAuthreqDTO;
import com.Application.todolistapp.RequestDTO.UserReqDTO;
import com.Application.todolistapp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticatonController {

    @Autowired
   public AuthenticationService authenticationservice;

   @Autowired
   PasswordEncoder passwordEncoder;

    @PostMapping("/api/signup")
    public String signup(@ModelAttribute("userauthReqDTO")UserAuthreqDTO userauthReqDTO){
        System.out.println("entered api/singpup controller");
        userauthReqDTO.setPassword(passwordEncoder.encode(userauthReqDTO.getPassword()));
        authenticationservice.saveUser(userauthReqDTO);
        ResponseEntity.ok("USer registered successfully");
        return "LoginForm";
    }


}
