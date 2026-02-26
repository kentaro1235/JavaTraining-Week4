package com.example.taskapp.controller;

import com.example.taskapp.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskErrorControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(TaskNotFoundException ex, Model model) {
        model.addAttribute("message", "指定したタスクが見つかりませんでした。");
        return "error/404";
    }
}