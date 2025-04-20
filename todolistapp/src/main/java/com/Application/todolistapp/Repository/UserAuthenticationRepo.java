package com.Application.todolistapp.Repository;

import com.Application.todolistapp.Entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticationRepo extends JpaRepository<UserAuthEntity,Long> {
    public UserAuthEntity findUserAuthEntityByUsername(String username);

}
