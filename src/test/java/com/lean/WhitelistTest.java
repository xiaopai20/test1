package com.lean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WhitelistTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getWithIncorrectToken() throws Exception {

        this.mockMvc.perform(get("/accounts/1").header("lean-token", "incorrect-token"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getWithoutToken() throws Exception {

        this.mockMvc.perform(get("/accounts/1"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void getWithValidToken() throws Exception {

        this.mockMvc.perform(get("/accounts/1").header("lean-token", "adf098adsfa098ss"))
                .andDo(print()).andExpect(status().isOk());
    }
}
