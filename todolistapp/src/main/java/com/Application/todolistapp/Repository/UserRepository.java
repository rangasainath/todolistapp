package com.Application.todolistapp.Repository;

import com.Application.todolistapp.Entity.User1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User1,Integer>
{
        public User1 findUserByEmailidAndPassword(String emailid, String password);
//        public User findUserBypassword(String password);
}

