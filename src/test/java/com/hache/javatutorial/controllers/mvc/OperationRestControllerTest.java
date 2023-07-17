package com.hache.javatutorial.controllers.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hache.javatutorial.dto.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationRestController.class)
class OperationRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void pathVariableTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/operation/path/{id}", "2"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void requestParamTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/operation/param").param("id","horacio"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void postTest() throws Exception {
        Persona user = Persona.builder().id("1").name("horacio").age(41).build();
        String userJson = new ObjectMapper().writeValueAsString(user);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/operation/post")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}