package com.mtwesley.dictator.service;

import com.mtwesley.dictator.model.player.Player;
import com.mtwesley.dictator.repository.PlayerRepository;
import com.mtwesley.dictator.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new User(player.getUsername(), player.getHash(), Collections.emptyList());
    }

    public String refresh(String refreshToken) throws Exception {
        String username = tokenUtil.extractUsername(refreshToken);
        UserDetails userDetails = loadUserByUsername(username);
        if (!tokenUtil.validateToken(refreshToken, userDetails)) {
            throw new Exception("Invalid Refresh Token");
        }
        return tokenUtil.generateToken(userDetails.getUsername());
    }

    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return tokenUtil.generateToken(userDetails.getUsername());
    }

    public Player register(String username, String password) {
        if (playerRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        Player newPlayer = new Player();
        newPlayer.setUsername(username);
        newPlayer.setHash(passwordEncoder.encode(password));
        playerRepository.save(newPlayer);
        return newPlayer;
    }
}
