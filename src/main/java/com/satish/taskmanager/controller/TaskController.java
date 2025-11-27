package com.satish.taskmanager.controller;

import com.satish.taskmanager.model.Task;
import com.satish.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Task create(@Valid @RequestBody TaskRequest req) {
        return service.create(req.title());
    }

    @PutMapping("/{id}")
    public Task toggle(@PathVariable int id) {
        return service.toggle(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        boolean removed = service.delete(id);
        return removed ? "Deleted" : "Not Found";
    }

    record TaskRequest(@NotBlank String title) {}
}
