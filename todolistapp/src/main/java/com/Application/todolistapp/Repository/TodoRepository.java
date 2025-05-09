package com.Application.todolistapp.Repository;

import com.Application.todolistapp.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {

    public List<Todo> findAll();
    public List<Todo> findTodoBycreatedby(int id);


}
