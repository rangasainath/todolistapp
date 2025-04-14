package com.Application.todolistapp.RequestDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserReqDTO {

    int id;
    String name;
    Long phoneNo;
    String emailid;
    String password;
}
