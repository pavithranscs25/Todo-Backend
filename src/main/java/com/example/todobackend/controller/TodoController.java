package com.example.todobackend.controller;

import com.example.todobackend.dto.CreateTodoRequest;
import com.example.todobackend.model.TodoClass;
import com.example.todobackend.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping
    public List<TodoClass> getAll(){
        return todoService.getAll();
    }

    @PostMapping
    public ResponseEntity<TodoClass> create(@Valid @RequestBody CreateTodoRequest createTodoRequest){
        TodoClass todoClass=new TodoClass();
        todoClass.setTitle(createTodoRequest.getTitle());
        todoClass.setCompleted(createTodoRequest.isCompleted());
        TodoClass created = todoService.create(todoClass);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoClass> update(@PathVariable int id, @Valid @RequestBody CreateTodoRequest createTodoRequest){
        TodoClass todoClass=new TodoClass();
        todoClass.setTitle(createTodoRequest.getTitle());
        todoClass.setCompleted(createTodoRequest.isCompleted());
        TodoClass updated=todoService.update(id, todoClass);
        if(updated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        boolean deleted=todoService.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
