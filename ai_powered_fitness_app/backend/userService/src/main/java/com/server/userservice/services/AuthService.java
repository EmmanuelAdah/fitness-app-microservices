package com.server.userservice.services;

import com.server.userservice.data.models.User;
import com.server.userservice.data.repositories.UserRepository;
import com.server.userservice.dtos.requests.LoginRequest;
import com.server.userservice.dtos.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

import static com.server.userservice.utils.Validator.isValidRequest;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AtomicReference<JwtService> jwtService = new AtomicReference<JwtService>();
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final ModelMapper modelMapper;


    public String register(RegisterRequest request){
        isValidRequest(request);
        User mappedUser = modelMapper.map(request, User.class);

        User savedUser = repository.save(mappedUser);
        return jwtService.get().generateToken(savedUser);
    }

    public String login(LoginRequest request) {

        User user;

        try {
            user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())
        ) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.get().generateToken(user);
    }
}
