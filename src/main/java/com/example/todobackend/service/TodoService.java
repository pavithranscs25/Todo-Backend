package com.example.todobackend.service;

import com.example.todobackend.model.TodoClass;
import com.example.todobackend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }

    public List<TodoClass> getAll(){
        return todoRepository.findAll();
    }

    public TodoClass create(TodoClass todoClass){
        return todoRepository.save(todoClass);
    }

    public TodoClass update(int id, TodoClass newData){
        TodoClass todo=todoRepository.findById(id).orElse(null);
        if(todo == null){
            return null;
        }
        todo.setTitle(newData.getTitle());
        todo.setCompleted(newData.isCompleted());
        return todoRepository.save(todo);
    }

    public boolean delete(int id){
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
