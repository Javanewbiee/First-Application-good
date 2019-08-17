package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Test
    public void testGetAllTasks() {
        //Given

        //When
        List<Task>list = dbService.getAllTasks();
        //Then
        Assert.assertEquals(11,list.size());
    }
    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(12L,"test4","test4");
        dbService.saveTask(task);
        //When
        List<Task>list = dbService.getAllTasks();
        //Then
        Assert.assertEquals(11,list.size());
        //CleanUp
        long id = task.getId();
        dbService.deleteTask(id);
    }
    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(16L, "test16", "test16");
        long id = task.getId();
        dbService.saveTask(task);
        //When
        Optional<Task>gotTask = dbService.getTask(id);
        //Then
        Assert.assertEquals(task.getTitle(),gotTask.get().getTitle());
        //CleanUp
        dbService.deleteTask(id);
    }
    @Test
    public void testDeleteTask() {
        //Given
        Task task = new Task(13L,"test13","test13");
        dbService.saveTask(task);
        long id = task.getId();
        //When
        dbService.deleteTask(id);
        //Then
        List<Task>list = dbService.getAllTasks();
        Assert.assertEquals(11,list.size());
    }
}
