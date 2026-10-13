package com.example.todobackend.repository;

import com.example.todobackend.model.TodoClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoClass, Integer> {

}
