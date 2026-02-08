package com.example.taskapp.service;

import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task create(Task task) {
        task.setId(null);
        return repo.save(task);
    }

    public Optional<Task> findById(Long id) {
    return repo.findById(id);
    }

    @Transactional
    public Task update(Long id, Task request) {
        Task existing = repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        existing.setTitle(request.getTitle());
        existing.setCompleted(request.isCompleted());
        return existing;
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repo.deleteById(id);
    }
}