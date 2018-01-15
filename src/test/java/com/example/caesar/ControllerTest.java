package com.example.caesar;

import com.example.caesar.service.CaesarService;
import com.example.caesar.service.CaesarServiceExeption;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = CaesarCypherApp.class
)
@AutoConfigureMockMvc
public class ControllerTest {

    @MockBean
    CaesarService caesarService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws CaesarServiceExeption {
        Mockito.when(caesarService.encode(Mockito.anyString(), Mockito.anyString())).thenReturn("encode");
        Mockito.when(caesarService.decode(Mockito.anyString(), Mockito.anyString())).thenReturn("decode");
    }

    @Test
    public void encode() throws Exception {
        assertResult(this.mockMvc.perform(get("/encode").param("query", "Encode fuzzy").param("locale", "en")
        ).andExpect(status().isOk()), "encode");
    }

    @Test
    public void decode() throws Exception {
        assertResult(this.mockMvc.perform(get("/decode").param("query", "Encode fuzzy").param("locale", "en")
        ).andExpect(status().isOk()), "decode");
    }


    private void assertResult(ResultActions perform, String result) throws Exception {
        String content = perform
                .andReturn().getResponse().getContentAsString();
        assert content.contains(result);
    }
}
