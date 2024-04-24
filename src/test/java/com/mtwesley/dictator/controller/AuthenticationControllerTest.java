package com.mtwesley.dictator.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.model.security.LoginRequest;
import com.mtwesley.dictator.model.security.RefreshRequest;
import com.mtwesley.dictator.model.security.RegisterRequest;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setUp() {
//        // Mocking password encoding for consistency in tests
//        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//    }

    @Test
    void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user", "pass");

        when(authenticationService.login("user", "pass")).thenReturn("mockToken");

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mockToken"));

        verify(authenticationService).login("user", "pass");
    }

    @Test
    void testRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("newUser", "newPass");

        Player mockPlayer = new Player();
        mockPlayer.setUsername("newUser");
        mockPlayer.setHash("newPass");

        when(authenticationService.register(anyString(), anyString())).thenReturn(mockPlayer);
        when(authenticationService.login("newUser", "newPass")).thenReturn("mockToken");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mockToken"));

        verify(authenticationService).register("newUser", "newPass");
        verify(authenticationService).login("newUser", "newPass");
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testRefreshToken() throws Exception {
        when(authenticationService.refresh("mockToken")).thenReturn("newMockToken");

        RefreshRequest refreshRequest = new RefreshRequest("mockToken");
        mockMvc.perform(post("/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("newMockToken"));

        verify(authenticationService).refresh("mockToken");
    }
}
