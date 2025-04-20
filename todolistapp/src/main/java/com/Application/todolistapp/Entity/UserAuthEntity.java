package com.Application.todolistapp.Entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user_auth")
public class UserAuthEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        return List.of(new SimpleGrantedAuthority(role));

    }



    @Override
    public boolean isAccountNonExpired(){
       return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
       return true;
    }

    @Override
    public boolean isEnabled(){
       return true;
    }

    @Override
    public String  getUsername(){
       return username;
    }



    public void  setUsername(String username){
        this.username = username;
    }


    @Override
    public String getPassword() {
        return password;
    }



    public void setPassword(String password)
    {
        this.password = password;
    }


    public String getRole(){
       return this.role;
    }

    public void setRole(String role){
       this.role= role;
    }



}
