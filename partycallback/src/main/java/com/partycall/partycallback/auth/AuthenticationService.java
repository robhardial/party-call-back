package com.partycall.partycallback.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.partycall.partycallback.models.Role;
import com.partycall.partycallback.models.User;
import com.partycall.partycallback.repositiories.UserRepository;
import com.partycall.partycallback.services.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.USER);

        userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(newUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
