package com.Application.todolistapp.Service;

import com.Application.todolistapp.Entity.Todo;
import com.Application.todolistapp.Repository.TodoRepository;
import com.Application.todolistapp.RequestDTO.TodoReqDTO;
import com.Application.todolistapp.ResponseDTO.TodoRespDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class   TodoService {

   private final TodoRepository taskrepository;
   public TodoService(TodoRepository taskrepository){
      this.taskrepository= taskrepository;
   }


   public TodoRespDTO createTaskService(TodoReqDTO taskreqdto){
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
      return taskrespdto;

   }


  public TodoRespDTO getTaskService(int id){

      var fetchedToDo= taskrepository.findById(id);
      Todo todo = fetchedToDo.get();
      var TodoRespDTO = new TodoRespDTO();
      TodoRespDTO.setId(todo.getId());
      TodoRespDTO.setTaskName(todo.getTaskName());
      return TodoRespDTO;


  }

    public List<TodoRespDTO> getAllTasksService(){

        var fetchedToDo= taskrepository.findAll(Sort.by(Sort.Order.asc("taskName")));
        List<Todo> todo = fetchedToDo.stream().toList();
        List<TodoRespDTO> todorespdto = new ArrayList<TodoRespDTO>();
        for(Todo todoele : todo){
            var TodoRespDTO = new TodoRespDTO();
            TodoRespDTO.setId(todoele.getId());
            TodoRespDTO.setTaskName(todoele.getTaskName());
            TodoRespDTO.setDescription(todoele.getDescription());
            TodoRespDTO.setStatus(todoele.getStatus());
            todorespdto.add(TodoRespDTO);
        }
        return todorespdto;

    }

    public TodoRespDTO deleteTasksService(int id){
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
        return ToDoRespdto;
    }


    public List<TodoRespDTO> deleteallTasksService(){
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
            todorespdto.add(todorespdtoobj);
        }

        return todorespdto;
    }

    public TodoRespDTO updateTasksService(TodoReqDTO todoreqdto){
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
       }
       return todorespdto;

    }

}
