package com.Application.todolistapp.RequestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

@NoArgsConstructor
@Getter
@Setter
public class TodoReqDTO {

    int id;
    String taskName;
    String description;
    String status;
    LocalDateTime creationTime;

}
