package com.Application.todolistapp.Filters;

import com.Application.todolistapp.RequestDTO.JwtAuthenticationToken;
import com.Application.todolistapp.Util.JWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTValidationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;



    public  JWTValidationFilter(AuthenticationManager authenticationManager){
     this.authenticationManager = authenticationManager;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        String token = extractJwtFormRequest(request);
        if(token != null){
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
            Authentication authResult = authenticationManager.authenticate(authenticationToken);
            if(authResult.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        filterChain.doFilter(request, response);





    }


    private String extractJwtFormRequest(HttpServletRequest request){
//        String bearerToken = request.getHeader("Authorization");
//        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
//            return bearerToken.substring(7);
//
//        }

        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        String refreshToken = null;
        for(Cookie cookie : cookies){
            if("tempToken". equals(cookie.getName())){
                refreshToken = cookie.getValue();
            }
        }
        return refreshToken;


    }

}
