package com.Application.todolistapp.Filters;

import com.Application.todolistapp.Entity.UserAuthEntity;
import com.Application.todolistapp.RequestDTO.JwtAuthenticationToken;
import com.Application.todolistapp.Util.JWTUtility;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTRefreshFilter extends OncePerRequestFilter {

    private final JWTUtility jwtutil;
    private final AuthenticationManager authenticationmanger;

    public JWTRefreshFilter(AuthenticationManager authenticationmanger,JWTUtility jwtutil){
        this.authenticationmanger = authenticationmanger;
        this.jwtutil = jwtutil;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

        if(!request.getServletPath().equals("/refresh-token")){
            filterChain.doFilter(request,response);
            return;
        }

        String refreshToken = extractJwtFromRequest(request);
        if(refreshToken == null)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        }

        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(refreshToken);
        Authentication authResult = authenticationmanger.authenticate(authenticationToken);
        if(authResult.isAuthenticated()){
            String newToken = jwtutil.generateToken(authResult.getName(),15,(UserAuthEntity)authResult.getPrincipal());
            response.setHeader("Authorization", "Bearer "+newToken);
        }
    }


    public String extractJwtFromRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        String refreshToken = null;
        for(Cookie cookie : cookies){
            if("refreshToken". equals(cookie.getName())){
                refreshToken = cookie.getValue();
            }
        }
        return refreshToken;
    }

}
