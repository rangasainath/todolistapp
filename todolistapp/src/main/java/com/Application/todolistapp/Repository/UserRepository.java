package com.Application.todolistapp.Repository;

import com.Application.todolistapp.Entity.Todo;
import com.Application.todolistapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
        public  User findUserByEmailidAndPassword(String emailid, String password);
//        public User findUserBypassword(String password);
}

