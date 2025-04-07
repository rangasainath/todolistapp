package com.InterviewPrep.todolistapp.Contoller;

import com.InterviewPrep.todolistapp.RequestDTO.TodoReqDTO;
import com.InterviewPrep.todolistapp.ResponseDTO.TodoRespDTO;
import com.InterviewPrep.todolistapp.Service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
     TodoService taskservice;

     public TodoController(TodoService taskservie){

         this.taskservice = taskservice;
     }

    @PostMapping("api/v1/task/")
    public TodoRespDTO createTaskController(@RequestBody TodoReqDTO taskreqdto)
    {
       return taskservice.createTaskService(taskreqdto);
    }










}
