package com.Application.todolistapp;

import com.Application.todolistapp.Filters.JWTAuthenticationFilter;
import com.Application.todolistapp.RequestDTO.JwtAuthenticationToken;
import com.Application.todolistapp.Util.JWTUtility;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JWTAuthenticationProvider implements AuthenticationProvider {
private final JWTUtility jwtutil;
private final UserDetailsService userDetailsService;
    public JWTAuthenticationProvider(JWTUtility jwtutil, UserDetailsService userDetailsService)
    {
        this.jwtutil = jwtutil;
        this.userDetailsService = userDetailsService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String token = ((JwtAuthenticationToken) authentication).getToken();
        String username = jwtutil.validateAndExtractUsername(token);
        if(username == null) {
            throw new BadCredentialsException("Invalid JWT Token");
        }

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      return new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
    }


   @Override
    public boolean supports(Class<?> authentication){
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);

   }





}
