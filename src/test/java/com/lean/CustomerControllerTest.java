package com.lean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistCustomer() throws Exception {

        this.mockMvc.perform(get("/customers/2").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"customerId\":2,\"name\":\"Jane Bloggs\",\"nationality\":\"BRITISH\"," +
                                "\"email\":\"user2@leantech.me\",\"address\":" +
                                "\"140 Tabernacle Street, Shoreditch EC2A 4SD, London, UK\"," +
                                "\"birthDay\":\"1987-09-22\"}"));
    }

    @Test
    public void getNotExitCustomer() throws Exception {

        this.mockMvc.perform(get("/customers/111").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isNotFound());
    }


}