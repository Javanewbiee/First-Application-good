package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task>getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long Id) {
        List <Task> list = repository.findAll();
        List<Task> filteredList = list.stream()
                .filter(task -> task.getId() == Id)
                .collect(Collectors.toList());
        Task task = filteredList.get(0);
        return task;
    }
}
