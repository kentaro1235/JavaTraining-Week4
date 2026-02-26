package com.example.taskapp.controller;

import com.example.taskapp.controller.dto.TaskForm;
import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 一覧：GET /tasks
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/index";
    }

    // 新規フォーム：GET /tasks/new
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new TaskForm());
        model.addAttribute("mode", "create");
        return "tasks/form";
    }

    // 作成：POST /tasks
    @PostMapping
    public String create(@Valid @ModelAttribute("form") TaskForm form,
                         BindingResult bindingResult,
                         RedirectAttributes attributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "create");
            return "tasks/form";
        }

        Task task = new Task();
        task.setTitle(form.getTitle());
        task.setCompleted(form.isCompleted());

        taskService.create(task);
        attributes.addFlashAttribute("success", "登録しました");
        return "redirect:/tasks";
    }

    // 編集フォーム：GET /tasks/{id}/edit
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        TaskForm form = new TaskForm();
        form.setTitle(task.getTitle());
        form.setCompleted(task.isCompleted());

        model.addAttribute("form", form);
        model.addAttribute("taskId", id);
        model.addAttribute("mode", "edit");
        return "tasks/form";
    }

    // 更新：POST /tasks/{id}
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("form") TaskForm form,
                         BindingResult bindingResult,
                         RedirectAttributes attributes,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", id);
            model.addAttribute("mode", "edit");
            return "tasks/form";
        }

        Task request = new Task();
        request.setTitle(form.getTitle());
        request.setCompleted(form.isCompleted());

        taskService.update(id, request);
        attributes.addFlashAttribute("success", "更新しました");
        return "redirect:/tasks";
    }

    // 削除：POST /tasks/{id}/delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        taskService.delete(id);
        attributes.addFlashAttribute("success", "削除しました");
        return "redirect:/tasks";
    }

    // 任意：完了切替：POST /tasks/{id}/toggle
    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes attributes) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task request = new Task();
        request.setTitle(task.getTitle());
        request.setCompleted(!task.isCompleted());

        taskService.update(id, request);
        attributes.addFlashAttribute("success", "完了状態を更新しました");
        return "redirect:/tasks";
    }
}