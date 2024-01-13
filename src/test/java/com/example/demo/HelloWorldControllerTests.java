package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNewsFlashJson() throws Exception {
        mockMvc.perform(get("/newsflash"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("News 1"))
                .andExpect(jsonPath("$.company").value("Company A"))
                .andExpect(jsonPath("$.text").value("Lorem ipsum 1"));
    }

    @Test
    public void shouldReturnHelloWorldOnPostRequest() throws Exception {
        mockMvc.perform(post("/newsflash")
                .content("{\"title\":\"Sample Title\",\"company\":\"Sample Company\",\"text\":\"Sample Text\"}")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Received NewsFlash: Title=Sample Title, Company=Sample Company, Text=Sample Text")));
    }

    @Test
    public void shouldReturnErrorOnInvalidPostRequest() throws Exception {
        mockMvc.perform(post("/newsflash")
                .content("Invalid JSON")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
