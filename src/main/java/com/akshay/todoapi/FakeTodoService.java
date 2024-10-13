package com.akshay.todoapi;

import org.springframework.stereotype.Service;

@Service
public class FakeTodoService implements ToDoService{
    @Override
    public String doSomething() {
        return "Do Something";
    }
}
