package com.example.hellospring.task;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    public List<Task> findAll(){
        return tasks;
    }

    public Task addTask(String title){
        int id = counter.incrementAndGet();
        Task newTask = new Task(id, title);
        tasks.add(newTask);
        return newTask;
    }

}
