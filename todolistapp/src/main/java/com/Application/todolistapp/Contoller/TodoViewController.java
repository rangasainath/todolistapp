package com.Application.todolistapp.Contoller;


import com.Application.todolistapp.Entity.Todo;
import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import com.Application.todolistapp.Service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Element;
import java.util.List;

@Controller
public class TodoViewController{
    TodoService todoservice;

     TodoViewController(TodoService todoservice){
         this.todoservice = todoservice;
     }

    @GetMapping("/getdata")
     public String getTodo(Model model){

        List<TodoRespDTO> todorespdto = todoservice.getAllTodos();
       model.addAttribute("todos", todorespdto);

        return "TodoPage";
    }

    @GetMapping("/{id}/delete")
    public  String deleteTodo(@PathVariable Integer id){
        System.out.println("This is from delete tasks function");
        todoservice.deleteTodos(id);
        return "redirect:/getdata";
    }

    @PostMapping("/postdata")
    public String createTodo(@ModelAttribute("todoreqdto") TodoReqDTO  todoreqdto,Model model){
      System.out.println("todo:"+ todoreqdto);
         TodoRespDTO todorespdto = todoservice.createTodos(todoreqdto);
//        model.addAttribute("todos", todorespdto);
         return "redirect:/getdata";
    }

    @GetMapping("/createtodo")
    public String createtview(Model model){
         model.addAttribute("todoreqdto", new TodoReqDTO());
         return "createnewTask";
    }
}
