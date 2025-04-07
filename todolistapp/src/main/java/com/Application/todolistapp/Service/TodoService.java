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
public class TodoService {

   private final TodoRepository taskrepository;
   public TodoService(TodoRepository taskrepository){
      this.taskrepository= taskrepository;
   }


   public TodoRespDTO createTaskService(TodoReqDTO taskreqdto){
      var task = new Todo();
      task.setTaskName(taskreqdto.getTaskName());
      var task1 = taskrepository.save(task);
      var taskrespdto = new TodoRespDTO();
      taskrespdto.setId(task1.getId());
      taskrespdto.setTaskName(task1.getTaskName());
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
            todorespdto.add(TodoRespDTO);
        }
        return todorespdto;

    }


}
