package com.mtwesley.dictator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.repository.PlayerRepository;
import com.mtwesley.dictator.security.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
class AuthenticationServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenUtil tokenUtil;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterNewUser_Success() {
        when(playerRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        Player player = new Player();
        player.setUsername("testUser");
        player.setHash("encodedPassword");

        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player result = authenticationService.register("Test User", "testUser", "password");
        assertNotNull(result);
        assertEquals("Test User", result.getName());
        assertEquals("testUser", result.getUsername());
        assertEquals("encodedPassword", result.getHash());
    }

    @Test
    void testLogin_Success() {
        UserDetails userDetails = new User("testUser", "password", Collections.emptyList());
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(tokenUtil.generateToken("testUser")).thenReturn("fakeToken");

        String token = authenticationService.login("testUser", "password");
        assertNotNull(token);
        assertEquals("fakeToken", token);
    }

    @Test
    void testUsernameExists() {
        when(playerRepository.existsByUsername("existingUser")).thenReturn(true);
        when(playerRepository.existsByUsername("nonExistingUser")).thenReturn(false);

        assertTrue(authenticationService.usernameExists("existingUser"));
        assertFalse(authenticationService.usernameExists("nonExistingUser"));

        verify(playerRepository).existsByUsername("existingUser");
        verify(playerRepository).existsByUsername("nonExistingUser");
    }

}
