package com.lean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistAccount() throws Exception {

        this.mockMvc.perform(get("/accounts/2").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"accountId\":2,\"customerId\":1,\"type\":\"Checking\",\"number\":" +
                                "\"789654123052\",\"iban\":\"SA000000789654123052\",\"status\":" +
                                "\"CLOSED\",\"balance\":0.0,\"currencyCode\":\"GBP\"}"));
    }

    @Test
    public void getNotExitAccount() throws Exception {

        this.mockMvc.perform(get("/accounts/111").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isNotFound());
    }

}