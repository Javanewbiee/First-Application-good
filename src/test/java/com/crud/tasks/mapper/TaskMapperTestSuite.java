package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTestSuite {

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"test","test task");
        TaskMapper taskMapper = new TaskMapper();
        //When
        Task result = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals(taskDto.getTitle(),result.getTitle());
    }
    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L,"test","test task");
        TaskMapper taskMapper = new TaskMapper();
        //When
        TaskDto result = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals(task.getTitle(),result.getTitle());
    }
    @Test
    public void testMapToTaskDtoList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test", "test task"));
        TaskMapper taskMapper = new TaskMapper();
        //When
        List<TaskDto> dtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, dtoList.size());
    }
}
