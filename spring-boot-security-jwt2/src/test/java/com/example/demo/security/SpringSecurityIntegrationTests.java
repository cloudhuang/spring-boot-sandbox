package com.example.demo.security;

import com.example.demo.web.payload.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringSecurityIntegrationTests {
    private MockMvc mockMvc;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");

        String jsonRequest = objectMapper.writeValueAsString(loginRequest);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testLoginWithWrongUsername() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("not_user");
        loginRequest.setPassword("wrong_pwd");

        String jsonRequest = objectMapper.writeValueAsString(loginRequest);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden()).andReturn();

        assertEquals(403, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("wrong_pwd");

        String jsonRequest = objectMapper.writeValueAsString(loginRequest);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden()).andReturn();

        assertEquals(403, mvcResult.getResponse().getStatus());
    }

}
