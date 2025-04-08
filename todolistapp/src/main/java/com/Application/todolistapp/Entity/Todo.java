package com.Application.todolistapp.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @SequenceGenerator( name="userId_Generator", sequenceName="userId_Sequence", initialValue=1, allocationSize=5)
    int id;
    String taskName;
    String description;
    String status;


}
