package com.akshay.todoapi;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("AnotherToDoService")
public class AnotherToDoService implements ToDoService{
    @Override
    public String doSomething() {
        return "From Another To Do Service";
    }
}
