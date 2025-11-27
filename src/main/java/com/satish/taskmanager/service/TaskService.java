package com.satish.taskmanager.service;

import com.satish.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private Map<Integer, Task> db = new HashMap<>();
    private int idCounter = 1;

    public List<Task> getAll() {
        return new ArrayList<>(db.values());
    }

    public Task create(String title) {
        Task task = new Task(idCounter++, title, false);
        db.put(task.getId(), task);
        return task;
    }

    public Task toggle(int id) {
        Task task = db.get(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
        }
        return task;
    }

    public boolean delete(int id) {
        return db.remove(id) != null;
    }
}
