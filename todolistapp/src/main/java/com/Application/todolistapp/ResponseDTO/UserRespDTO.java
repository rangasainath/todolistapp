package com.Application.todolistapp.ResponseDTO;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRespDTO {

    @Id
    int id;
    String Name;
    Long phoneNo;
    String emailid;
    String password;
}
