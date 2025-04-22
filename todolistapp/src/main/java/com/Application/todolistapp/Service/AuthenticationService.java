package com.Application.todolistapp.Service;

import com.Application.todolistapp.Entity.UserAuthEntity;
import com.Application.todolistapp.Repository.UserAuthenticationRepo;
import com.Application.todolistapp.RequestDTO.UserAuthreqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserAuthenticationRepo userauthenticationrepo;

    public UserDetails saveUser(UserAuthreqDTO userauthreqdto) {
        UserAuthEntity userauthentity = new UserAuthEntity();
        userauthentity.setUsername(userauthreqdto.getUsername());
        userauthentity.setPassword(userauthreqdto.getPassword());
        userauthentity.setEmailid(userauthreqdto.getEmailid());
        userauthentity.setRole(userauthreqdto.getRole());
        return userauthenticationrepo.save(userauthentity);
    }

    @Override
    public  UserAuthEntity loadUserByUsername(String username)throws UsernameNotFoundException {

         UserAuthEntity userauthentity  = new UserAuthEntity();
                 userauthentity=userauthenticationrepo.findUserAuthEntityByUsername(username);

                         if(userauthentity!= null){
                             return userauthentity;
                         }
                         else{
                             throw new UsernameNotFoundException("User not found");
                         }

    }
}
