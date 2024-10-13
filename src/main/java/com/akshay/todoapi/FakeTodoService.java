package com.akshay.todoapi;

import org.springframework.stereotype.Service;

@Service("FakeTodoService")
public class FakeTodoService implements ToDoService{
    @Override
    public String doSomething() {
        return "From Fake To Do Service";
    }
}
