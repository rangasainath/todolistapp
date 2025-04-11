package com.Application.todolistapp.ResponseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TodoRespDTO {

    int id;
    String taskName;
    String description;
    String status;
    LocalDateTime creationTime;

}

