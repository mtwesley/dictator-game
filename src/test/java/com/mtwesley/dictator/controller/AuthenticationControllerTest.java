package com.mtwesley.dictator.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.security.LoginRequest;
import com.mtwesley.dictator.model.security.RegisterRequest;
import com.mtwesley.dictator.security.SecurityUtils;
import com.mtwesley.dictator.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(AuthenticationController.class)
//@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PasswordEncoder passwordEncoder;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user", "pass");
        when(authenticationService.login("user", "pass")).thenReturn("mockToken");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mockToken"));

        verify(authenticationService, times(1)).login("user", "pass");
    }

    @Test
    void testRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("newUser", "newPass");

        Player mockPlayer = new Player(); // Assuming default constructor is available
        mockPlayer.setUsername("newUser");
        mockPlayer.setHash(passwordEncoder.encode("newPass")); // Mock the encoding process

        when(authenticationService.register("newUser", "newPass")).thenReturn(mockPlayer);
        when(authenticationService.login("newUser", "newPass")).thenReturn("mockToken");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mockToken"));

        verify(authenticationService, times(1)).register("newUser", "newPass");
        verify(authenticationService, times(1)).login("newUser", "newPass");
    }

    @Test
    void testRefreshToken() throws Exception {
        when(authenticationService.refresh("mockRefreshToken")).thenReturn("newMockToken");
        when(SecurityUtils.getCurrentUsername()).thenReturn("user");

        mockMvc.perform(post("/refresh")
                        .param("refreshToken", "mockRefreshToken"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("newMockToken"));

        verify(authenticationService, times(1)).refresh("mockRefreshToken");
    }
}

