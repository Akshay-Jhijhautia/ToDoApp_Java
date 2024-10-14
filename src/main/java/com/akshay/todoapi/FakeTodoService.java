package com.akshay.todoapi;

import org.springframework.stereotype.Service;

@Service("FakeTodoService")
public class FakeTodoService implements ToDoService{

    @Override
    @TimeMonitor
    public String doSomething() { // Join point
        for(int i = 0; i< 1000000000; i++) {} // To check execution time of this method
        return "From Fake To Do Service";
    }
}
