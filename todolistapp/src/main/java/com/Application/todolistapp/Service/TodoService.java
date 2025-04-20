package com.Application.todolistapp.Service;

import com.Application.todolistapp.Entity.Todo;
import com.Application.todolistapp.Entity.User1;
import com.Application.todolistapp.Repository.TodoRepository;
import com.Application.todolistapp.Repository.UserRepository;
import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.RequestDTO.UserReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import com.Application.todolistapp.ResponseDTO.UserRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class   TodoService {

   private final TodoRepository taskrepository;
   private final UserRepository userrepository;
   public TodoService(TodoRepository taskrepository, UserRepository userrepository){
      this.taskrepository= taskrepository;
      this.userrepository = userrepository;
   }


   public TodoRespDTO createTodos(TodoReqDTO taskreqdto){
       log.debug("This is debug message");

      var task = new Todo();
      task.setTaskName(taskreqdto.getTaskName());
      task.setDescription(taskreqdto.getDescription());
      task.setStatus(taskreqdto.getStatus());
      var task1 = taskrepository.save(task);
      var taskrespdto = new TodoRespDTO();
      taskrespdto.setId(task1.getId());
      taskrespdto.setTaskName(task1.getTaskName());
      taskrespdto.setDescription(task1.getDescription());
      taskrespdto.setStatus(task1.getStatus());
      taskrespdto.setCreationTime(task1.getCreationTime());

      return taskrespdto;
   }


  public TodoRespDTO getTodos(int id){

      var fetchedToDo= taskrepository.findById(id);
      Todo todo = fetchedToDo.get();
      var TodoRespDTO = new TodoRespDTO();
      TodoRespDTO.setId(todo.getId());
      TodoRespDTO.setTaskName(todo.getTaskName());
      TodoRespDTO.setDescription(todo.getDescription());
      TodoRespDTO.setStatus(todo.getStatus());
      TodoRespDTO.setCreationTime(todo.getCreationTime());
      return TodoRespDTO;


  }

    public List<TodoRespDTO> getAllTodos(){

        var fetchedToDo= taskrepository.findAll(Sort.by(Sort.Order.asc("creationTime")));
        List<Todo> todo = fetchedToDo.stream().toList();
        List<TodoRespDTO> todorespdto = new ArrayList<TodoRespDTO>();
        for(Todo todoele : todo){
            var TodoRespDTO = new TodoRespDTO();
            TodoRespDTO.setId(todoele.getId());
            TodoRespDTO.setTaskName(todoele.getTaskName());
            TodoRespDTO.setDescription(todoele.getDescription());
            TodoRespDTO.setStatus(todoele.getStatus());
            TodoRespDTO.setCreationTime(todoele.getCreationTime());
            todorespdto.add(TodoRespDTO);
        }
        return todorespdto;

    }

    public TodoRespDTO deleteTodos(int id){
        var findRequestedElement = taskrepository.findById(id);
        Todo requestedelement= null;
        if(findRequestedElement != null){
             requestedelement= findRequestedElement.get();
            taskrepository.deleteById(id);
        }
        var ToDoRespdto = new TodoRespDTO();
        ToDoRespdto.setId(requestedelement.getId());
        ToDoRespdto.setTaskName(requestedelement.getTaskName());
        ToDoRespdto.setDescription(requestedelement.getDescription());
        ToDoRespdto.setStatus(requestedelement.getStatus());
        ToDoRespdto.setCreationTime(requestedelement.getCreationTime());
        return ToDoRespdto;
    }


    public List<TodoRespDTO> deleteallTodos(){
       var tasks= taskrepository.findAll();
        List<Todo> todotasks = tasks.stream().toList();
        List<TodoRespDTO> todorespdto = new ArrayList<TodoRespDTO>();

        for(Todo todo: todotasks)
        {
            TodoRespDTO todorespdtoobj = new TodoRespDTO();
            todorespdtoobj.setId(todo.getId());
            todorespdtoobj.setTaskName(todo.getTaskName());
            todorespdtoobj.setDescription(todo.getDescription());
            todorespdtoobj.setStatus(todo.getStatus());
            todorespdtoobj.setCreationTime(todo.getCreationTime());
            todorespdto.add(todorespdtoobj);
        }
        taskrepository.deleteAll();
        return todorespdto;
    }


    public List<TodoRespDTO> deletemultipleTodos(int[] arr){
        List<Todo> todotasks = new ArrayList<Todo>();
        List<TodoRespDTO> todorespdto = new ArrayList<TodoRespDTO>();
       for(int i: arr) {
           var tasks = taskrepository.findById(i);
           Todo todo=tasks.get();
           TodoRespDTO todorespdtoobj = new TodoRespDTO();
           todorespdtoobj.setId(todo.getId());
           todorespdtoobj.setTaskName((todo.getTaskName()));
           todorespdtoobj.setStatus(todo.getStatus());
           todorespdtoobj.setDescription(todo.getDescription());
           todorespdtoobj.setCreationTime(todo.getCreationTime());
           todorespdto.add(todorespdtoobj);
           taskrepository.deleteById(i);

       }
//       Iterable<Integer> i=arr;

//        taskrepository.deleteAllByIdInBatch(arr);
       return todorespdto;

   }


   public void deletebatchTodos(List<Integer> todoids)
   {
//       List<Integer> arrOfIds= new ArrayList<Integer>();
//       int arr[];
//        for(TodoReqDTO todoreq: todoids)
//        {
//            arrOfIds.add(todoreq.getId());
//        }
//Iterable<Integer> iterable =   Arrays.stream(arrOfIds.toArray()).collect(Collectors.toList());
       Iterable<Integer> iterable = todoids;
       taskrepository.deleteAllByIdInBatch(iterable);
       System.out.println("All todos got deleted.");
   }

    public TodoRespDTO updateTodos(TodoReqDTO todoreqdto){
       var fetchedElement = taskrepository.findById(todoreqdto.getId());

        TodoRespDTO todorespdto = new TodoRespDTO();
       if(fetchedElement!=null){
           Todo todo = fetchedElement.get();
           todo.setTaskName(todoreqdto.getTaskName());
           todo.setDescription(todoreqdto.getDescription());
           todo.setStatus(todoreqdto.getStatus());
           var savedtodo=taskrepository.save(todo);
           todorespdto.setId(savedtodo.getId());
           todorespdto.setTaskName(savedtodo.getTaskName());
           todorespdto.setDescription(savedtodo.getDescription());
           todorespdto.setStatus(savedtodo.getStatus());
           todorespdto.setCreationTime(savedtodo.getCreationTime());
       }
       return todorespdto;

    }


    public boolean Authentication(UserReqDTO userreqdto){
       UserRespDTO userrespdto = new UserRespDTO();
       var query= userrepository.findUserByEmailidAndPassword(userreqdto.getEmailid(), userreqdto.getPassword());
//       var queriedpassword = userrepository.findUserBypassword(userreqdto.getPassword());
//       if(userrespdto.getEmailid() == userreqdto.getEmailid() && userrespdto.getPassword() == userreqdto.getEmailid()){
//
//       }
       if(query!=null){
           return true;
       }
       else{
           return false;
       }

    }


    public void signUp(UserReqDTO userreqdto){

       User1 user = new User1();
       user.setName(userreqdto.getName());
       user.setEmailid(userreqdto.getEmailid());
       user.setPhoneNo(userreqdto.getPhoneNo());
       user.setPassword(userreqdto.getPassword());
       var saved_user= userrepository.save(user);
       if(saved_user != null) {
           log.trace("user got saved with the details:" + saved_user);
       }
    }

}
