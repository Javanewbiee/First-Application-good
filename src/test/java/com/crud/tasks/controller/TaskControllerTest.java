package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskController taskController;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto>list = new ArrayList<>();

        when(taskController.getTasks()).thenReturn(list);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(0)));
    }
    @Test
    public void shouldGetTask() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L,"test","test content");

        when(taskController.getTask(1L)).thenReturn(task);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1L").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.title",is("test")))
                .andExpect(jsonPath("$.content",is("test content")));
    }
    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        TaskDto task = new TaskDto(1L,"test","test content");

        //when(taskController.deleteTask(1L)).
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask/1L").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}