package com.InterviewPrep.todolistapp.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator= "usersequence")
//    @SequenceGenerator(name="usersequence", sequenceName="usernosequance", initialValue = 1, allocationSize=10 )
    int userId;
    String userName;
//    @JoinColumn(name="taskid_userid", referencedColumnName ="id")
//    Todo task;
}


