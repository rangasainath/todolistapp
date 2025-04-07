package com.InterviewPrep.todolistapp.Entity;


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
//    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="userId_Generator")
//    @SequenceGenerator( name="userId_Generator", sequenceName="userId_Sequence", initialValue=1, allocationSize=5)
    int id;
    String taskName;

}
