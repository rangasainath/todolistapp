package com.Application.todolistapp.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @SequenceGenerator( name="userId_Generator", sequenceName="userId_Sequence", initialValue=1, allocationSize=5)
    int id;
    String taskName;
    String description;
    String status;
//    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp()
    LocalDateTime creationTime;

    int createdby;




}
