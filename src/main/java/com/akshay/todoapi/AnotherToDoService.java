package com.akshay.todoapi;

import org.springframework.stereotype.Service;

@Service
public class AnotherToDoService implements ToDoService{
    @Override
    public String doSomething() {
        return "From Another To Do Service";
    }
}
