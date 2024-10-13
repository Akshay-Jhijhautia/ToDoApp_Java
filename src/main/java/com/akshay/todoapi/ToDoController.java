package com.akshay.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {

    private static List<Todo> todoList;
    private ToDoService todoService;

    public ToDoController(ToDoService todoService) { // inject/send/pass an object of todo service in this controller function
        this.todoService = todoService;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        if(isCompleted == null) {
            return ResponseEntity.status(HttpStatus.OK).body(todoList);
        }

        List<Todo> filteredTodos = new ArrayList<>();
        for (Todo todo: todoList) {
            if(todo.isCompleted() == isCompleted) {
                filteredTodos.add(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(filteredTodos);
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

    @PatchMapping("/{todoId}")
    public ResponseEntity<Object> updateTodoById(@PathVariable Long todoId, @RequestBody HashMap<String,Object> map) {
        for(Todo todo: todoList) {
            if(todo.getId() == todoId) {
                map.forEach((key,value) -> {
                    switch (key) {
                        case "title":
                            if(value instanceof String) {
                                todo.setTitle((String) value);
                            }
                            break;
                        case "completed":
                            if(value instanceof Boolean) {
                                todo.setCompleted((Boolean) value);
                            }
                            break;
                        case "userId":
                            if(value instanceof Integer) {
                                todo.setUserId((Integer) value);
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid field: " + key);
                    }
                });
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
