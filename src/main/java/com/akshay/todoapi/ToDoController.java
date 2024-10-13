package com.akshay.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {

    private static List<Todo> todoList;

    public ToDoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

    @PostMapping()
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Object> getTodoById(@PathVariable Long todoId){
        for(Todo todo: todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        String errorMessage = "Resource with ID " + todoId + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Object> deleteTodoById(@PathVariable int todoId) {
        for(Todo todo: todoList) {
            if(todo.getId() == todoId) {
                todoList.remove(todo);
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        String errorMessage = "Resource with ID " + todoId + " not found.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
