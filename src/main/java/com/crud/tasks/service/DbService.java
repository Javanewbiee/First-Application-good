package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task>getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(final Long Id) {
        return repository.findById(Id).get();
    }
    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Optional<Task> getTask(Long id) {
        return repository.findById(id);
    }
}