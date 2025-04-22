package com.Application.todolistapp.RequestDTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthreqDTO {


    public String username;
    public String password;
    public String role;
    public String emailid;
}
