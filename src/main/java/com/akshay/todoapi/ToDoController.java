package com.akshay.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ToDoController {

    private static List<Todo> todoList;

    public ToDoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoList;
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return newTodo;
    }
}
