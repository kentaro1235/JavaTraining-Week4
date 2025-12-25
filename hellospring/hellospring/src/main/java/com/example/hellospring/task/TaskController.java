package com.example.hellospring.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    
    @GetMapping
    public List<Task> gettasks(){
        return taskService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Map<String, String> body){
        String title = body.get("title");
        if(title == null || title.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("ERROR", "タイトルを入力してください"));
        }
        Task newTask = taskService.addTask(title.trim());
        return ResponseEntity.ok(newTask);
    }
    
    
}
