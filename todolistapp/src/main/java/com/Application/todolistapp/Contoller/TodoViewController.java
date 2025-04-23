package com.Application.todolistapp.Contoller;


import com.Application.todolistapp.Entity.Todo;
import com.Application.todolistapp.Entity.UserAuthEntity;
import com.Application.todolistapp.RequestDTO.LoginRequest;
import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.RequestDTO.UserAuthreqDTO;
import com.Application.todolistapp.RequestDTO.UserReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import com.Application.todolistapp.Service.TodoService;
import org.springframework.security.core.context.SecurityContext;
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

    @GetMapping("/home")
    public String homePage(Model model){

        return "Home";
    }

    @GetMapping("/getdata")
     public String getTodo(Model model, SecurityContext securitycontext){
       UserAuthEntity userAuthEntity =(UserAuthEntity)securitycontext.getAuthentication().getPrincipal();


       List<TodoRespDTO> todorespdto = todoservice.getTodos(userAuthEntity.getId());
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
    public String createTodo(@ModelAttribute("todoreqdto") TodoReqDTO  todoreqdto,Model model,SecurityContext securityContext){
      System.out.println("todo:"+ todoreqdto);
         UserAuthEntity userAuthEntity = (UserAuthEntity)securityContext.getAuthentication().getPrincipal();

         TodoRespDTO todorespdto = todoservice.createTodos(todoreqdto,userAuthEntity);
//        model.addAttribute("todos", todorespdto);
         return "redirect:/getdata";
    }

    @GetMapping("/createtodo")
    public String createtview(Model model){
         model.addAttribute("todoreqdto", new TodoReqDTO());
         return "createnewTask";
    }
//Authentication controllers.
    @GetMapping("/directtologinform")
    public String loginUser(Model model){
         model.addAttribute("loginerequest",new LoginRequest());
         return "LoginForm";
    }

    @PostMapping("/authentication1")
    public String authenticate(@ModelAttribute("userReqDTO")UserReqDTO userReqDTO){
        boolean authenticationCheck =todoservice.Authentication(userReqDTO);
        // model.addAttribute("userreqdto",new UserReqDTO());
        return "redirect:/getdata";
    }

    @GetMapping("/directtosignup")
    public String signUp(Model model){
        model.addAttribute("userauthReqDTO",new UserAuthreqDTO());
        return "SignupForm";
    }
    @PostMapping("/signup")
    public String signup(@ModelAttribute("userReqDTO")UserReqDTO userReqDTO){
         todoservice.signUp(userReqDTO);
        // model.addAttribute("userreqdto",new UserReqDTO());
        return "redirect:/getdata";
    }


}
