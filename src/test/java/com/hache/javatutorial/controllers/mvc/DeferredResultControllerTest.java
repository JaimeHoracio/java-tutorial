package com.hache.javatutorial.controllers.mvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeferredResultController.class)
class DeferredResultControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("resolveBlocking")
    void resolveBlocking() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/resolve/blocking"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("ResolveNonBlocking")
    void resolveNonBlocking() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/resolve/non-blocking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();

        // Dado que este endpoint es asincronico, MockMvc no espera la respuesta
        // Por eso hay que hacer uso de asyncDispatch
        mvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("message").value("hello"));
                .andDo(MockMvcResultHandlers.print());
    }
}