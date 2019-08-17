package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskController taskController;
    @MockBean
    private TaskMapper taskMapper;
    @MockBean
    private DbService service;

    @Test
    public void shouldGetTasksEmpty() throws Exception {
        //Given
        List<Task>list = new ArrayList<>();

        when(service.getAllTasks()).thenReturn(list);
        //When & Then
        mockMvc.perform(get("/v1/tasks")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().is(200))
               .andExpect(jsonPath("$",hasSize(0)));
    }
    @Test
    public void shouldGetTasksFilled() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L,"title", "test");
        List<TaskDto>list = new ArrayList<>();
        list.add(task);

        when(taskController.getTasks()).thenReturn(list);
        //When & Then
        mockMvc.perform(get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(1)));
    }
    @Test
    public void shouldGetTask() throws Exception {
        //Given
       // TaskDto task = new TaskDto(1L,"test","test content");

        //when(taskController.getTask(1L)).thenReturn(task);
        //When & Then
        //mockMvc.perform(get("/v1/tasks/1")
          //      .contentType(MediaType.APPLICATION_JSON))
          //      .andExpect(status().is(200))
          //      .andExpect(jsonPath("$.title",is("test")))
          //      .andExpect(jsonPath("$.content",is("test content")));
    }
    @Test
    public void shouldDeleteTask() throws Exception {
        //Given

        //When & Then
          //mockMvc.perform(delete("/v1/tasks/1")
            //    .contentType(MediaType.APPLICATION_JSON))
             //   .andExpect(status().is(200));
    }
    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto updatedTask = new TaskDto(1L,"updated title", "updated content");

        Gson gson = new Gson();
        String json = gson.toJson(updatedTask);

        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(updatedTask);
        //When & Then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("updated title")))
                .andExpect(jsonPath("$.content",is("updated content")));
    }
    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L,"title","content");

        Gson gson = new Gson();
        String json = gson.toJson(task);

        //When & Then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("UTF-8"))
                .andExpect(status().is(200));
    }

}