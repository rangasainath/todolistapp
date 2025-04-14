package com.Application.todolistapp.Contoller;

import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import com.Application.todolistapp.Service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController  {
     TodoService todoservice;
     Logger logger = LoggerFactory.getLogger(TodoController.class);
     public TodoController(TodoService todoservice){

         this.todoservice = todoservice;
     }

    @PostMapping("api/v1/todo/")
    public TodoRespDTO createTodo(@RequestBody TodoReqDTO taskreqdto)

    {
       logger.trace("This function is going to createtodos-tracemessage");
       logger.info("This function is going to createtodo-infomessage");
       logger.warn("This function is going to createtodo-warnmessage");
       logger.debug("This function is going to createtodo-debugmessage");
       logger.error("This function is going to createtodo-errormessage");
       logger.info("this is info message form createtodo {}  ,  {}", 500, 400);
//       var except = new Exception("sample exception");

//       logger.error("this is an exception",except);
       logger.warn("this is warning message");
       logger.info("API run has started.");
       TodoRespDTO todorespdto = todoservice.createTodos(taskreqdto);

       return todorespdto;
    }

   @GetMapping("api/v1/todo/")
   public TodoRespDTO getTodos(@RequestParam(value="userid", required=true)int id){
         System.out.println("id passed by  user:"+id);
         return todoservice.getTodos(id);
   }

    @GetMapping("api/v1/todo/getAll")
    public List<TodoRespDTO> getAllTodos() {
        System.out.println("This is from get all tasks function");
        return todoservice.getAllTodos();
    }

    @DeleteMapping("api/v1/todo/")
    public TodoRespDTO deleteTodo(@RequestParam(value="taskid", required=true)int id){
         System.out.println("This is from delete tasks function");
         return todoservice.deleteTodos(id);
    }

    @DeleteMapping("api/v1/todo/deleteall")
    public List<TodoRespDTO> deleteAllTodos()
    {
        System.out.println("delete all tasks funtionality");
        return todoservice.deleteallTodos();
    }

    @DeleteMapping("api/v1/todo/batchdelete")
    public void deletebatchtodos(@RequestBody List<Integer> todoids){
         System.out.println("this is form batch delete api."+todoids);
          todoservice.deletebatchTodos(todoids);

    }

    @PutMapping("api/v1/task")
    public TodoRespDTO updatetasks(@RequestBody TodoReqDTO todoreqdto){
     System.out.println("this is from update tasks");
     return todoservice.updateTodos(todoreqdto);

    }



}
