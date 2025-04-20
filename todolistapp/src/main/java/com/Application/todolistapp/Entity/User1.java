package com.Application.todolistapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User1 {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String Name;
    Long phoneNo;
    String emailid;
    String password;

}
