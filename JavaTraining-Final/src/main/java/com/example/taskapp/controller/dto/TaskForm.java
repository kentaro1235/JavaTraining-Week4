package com.example.taskapp.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm {

    @NotBlank(message = "タイトルを入力してください")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    private String title;

    private boolean completed;

    public TaskForm() {}

    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}