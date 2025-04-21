package com.Application.todolistapp.Config;

import com.Application.todolistapp.Filters.JWTAuthenticationFilter;
import com.Application.todolistapp.Filters.JWTRefreshFilter;
import com.Application.todolistapp.Filters.JWTValidationFilter;
import com.Application.todolistapp.JWTAuthenticationProvider;
import com.Application.todolistapp.Util.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private JWTUtility jwtUtil;
    private UserDetailsService userDetailsService;

    @Autowired
    public  SecurityConfig(JWTUtility jwtutil, UserDetailsService userDetailsService){
        this.jwtUtil = jwtutil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationmanager, JWTUtility jwtutil) throws Exception {
        JWTAuthenticationFilter jwtauthfilter = new JWTAuthenticationFilter(authenticationmanager, jwtutil);
        JWTValidationFilter   jwtvalidfilter =  new JWTValidationFilter(authenticationmanager);
        JWTRefreshFilter jwtrefreshfilter = new JWTRefreshFilter(authenticationmanager,jwtutil);
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/signup").permitAll()
                                //.requestMatchers("/createtodo").hasRole("USER")
                                .anyRequest().authenticated()
                )

//                when we are using formbased authentication filter which will create session, if we want to manage the session
//                then we can use the below code which will allow the user to have one session in all browsers,
//                 as we are using the token method to atuthenticate the user requests we are not using the below code.
//                .sessionManagement(session->session
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(true))



                .csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtauthfilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtvalidfilter, JWTAuthenticationFilter.class)
                .addFilterAfter(jwtrefreshfilter,JWTValidationFilter.class)
                .httpBasic(Customizer.withDefaults());



//        http.authenticationProvider(new
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoauthenticationprovider = new DaoAuthenticationProvider();
        daoauthenticationprovider.setUserDetailsService(userDetailsService);
        daoauthenticationprovider.setPasswordEncoder(passwordEncoder());
        return  daoauthenticationprovider;
    }

    @Bean
    public JWTAuthenticationProvider jwtAuthenticationProvider(){
        return new JWTAuthenticationProvider(jwtUtil, userDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider(),jwtAuthenticationProvider()));
    }







}

