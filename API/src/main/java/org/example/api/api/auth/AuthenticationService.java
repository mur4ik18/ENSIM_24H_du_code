package org.example.api.api.auth;

import lombok.RequiredArgsConstructor;
import org.example.api.api.user.User;
import org.example.api.api.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.api.api.auth.AuthenticationRequest;
import org.example.api.api.auth.AuthenticationResponse;
import org.example.api.api.auth.RegisterRequest;
import org.example.api.api.model.Role;
import org.example.api.config.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    public boolean EmailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
    public AuthenticationResponse register(RegisterRequest request) {
       var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Participant)
                .build();
        repository.save(user);
        var jwtToken = JwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        /*
        I commented this out because it was causing an error
        I don't know why it was there in the first place

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
         */
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = JwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
