package com.Application.todolistapp.Filters;

import com.Application.todolistapp.RequestDTO.LoginRequest;
import com.Application.todolistapp.Util.JWTUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtility jwtutil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtility jwtUtil)
    {
        this.authenticationManager = authenticationManager;
        this.jwtutil = jwtUtil;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().equals("/generate-token")){
            filterChain.doFilter(request,response);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        LoginRequest loginrequest = objectMapper.readValue(request.getInputStream(),LoginRequest.class);

        UsernamePasswordAuthenticationToken auth =   new UsernamePasswordAuthenticationToken(loginrequest.getUsername(), loginrequest.getPassword());

        Authentication authResult = authenticationManager.authenticate(auth);

        if(authResult.isAuthenticated()){
            String token = jwtutil.generateToken(authResult.getName(), 15);
            response.setHeader("Authorization", "Bearer "+ token);

            String refreshToken=jwtutil.generateToken(authResult.getName(), 7*24*60);
            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setSecure(true);
            refreshCookie.setPath("/refresh-token");
            refreshCookie.setMaxAge(7*24*60*60);
            response.addCookie(refreshCookie);
        }

    }

}
