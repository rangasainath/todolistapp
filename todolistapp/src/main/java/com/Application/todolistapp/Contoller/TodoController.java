package com.Application.todolistapp.Contoller;

import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import com.Application.todolistapp.Service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
     TodoService todoservice;

     public TodoController(TodoService todoservice){

         this.todoservice = todoservice;
     }

    @PostMapping("api/v1/task/")
    public TodoRespDTO createTaskController(@RequestBody TodoReqDTO taskreqdto)
    {
       return todoservice.createTaskService(taskreqdto);
    }

   @GetMapping("api/v1/task/")
   public TodoRespDTO getTaskController(@RequestParam(value="userid", required=true)int id){
         System.out.println("id passed by  user:"+id);
         return todoservice.getTaskService(id);
   }

    @GetMapping("api/v1/task/getAll")
    public List<TodoRespDTO> getAllTaskController() {
        System.out.println("This is from get all tasks function");
        return todoservice.getAllTasksService();
    }

}
