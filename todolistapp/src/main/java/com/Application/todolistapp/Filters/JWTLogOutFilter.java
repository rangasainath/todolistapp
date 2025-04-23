package com.Application.todolistapp.Filters;

import com.Application.todolistapp.RequestDTO.JwtAuthenticationToken;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTLogOutFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationmanager;

    public JWTLogOutFilter(AuthenticationManager authenticationmanager){
        this.authenticationmanager = authenticationmanager;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)throws ServletException, IOException {
       if(!request.getServletPath().equals("")) {
           filterchain.doFilter(request, response);
           return;
       }

      String token = extractJwtFromRequest(request);
       if(token!= null){
           JwtAuthenticationToken authenticationtoken = new JwtAuthenticationToken(token);
           Authentication authresult =authenticationmanager.authenticate(authenticationtoken);
           if(authresult.isAuthenticated()){
               SecurityContextHolder.clearContext();
           }
//          request.logout();
       }




//        extractJwtFromRequest(request);

    }


    public String extractJwtFromRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();


        if(cookies == null){
            return null;
        }
        String refreshToken = null;
        for(Cookie cookie : cookies){

            if("tempToken". equals(cookie.getName())){
                refreshToken = cookie.getValue();
                if(refreshToken!=null)
                {
                    cookie.setValue("");
                }


            }
        }
        return refreshToken;
    }
}
