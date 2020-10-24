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
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistTrx() throws Exception {

        this.mockMvc.perform(get("/transactions/2").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"trxId\":2,\"accountId\":1,\"type\":\"Transfer\",\"description\":" +
                                "\"Transfer from Another Bank\",\"amount\":11.99,\"currencyCode\":\"SAR\"," +
                                "\"timestamp\":\"2020-05-01 12:44:03\"}"));
    }

    @Test
    public void getNotExitTrx() throws Exception {

        this.mockMvc.perform(get("/transactions/111").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void getExistCustomerTrx() throws Exception {

        this.mockMvc.perform(get("/accounts/2/transactions").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(
                        "[{\"trxId\":5,\"accountId\":2,\"type\":\"Direct Debit\",\"description\":\"Utility Payment\"," +
                                "\"amount\":-180.0,\"currencyCode\":\"GBP\",\"timestamp\":\"2020-06-11 01:55:01\"}]"));
    }

    @Test
    public void getNotExitCustomerTrx() throws Exception {

        this.mockMvc.perform(get("/accounts/111/transactions").header("lean-token", "45kh2345jh245hhk"))
                .andDo(print()).andExpect(status().isNotFound());
    }
}