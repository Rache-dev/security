package com.alibou.security.auth;

import com.alibou.security.config.JwtService;
import com.alibou.security.employee.EmployeeRepository;
import com.alibou.security.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;    //inject the repository to since we will interact with the database
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;      //to encode password
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    //regestring a user
    public AuthenticationResponse register(RegisterRequest request) {
        //create user object out of the RegisterRequest, save it to the database and return the token
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    //authenticate the user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //check if username and password are correct
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        //get user using findByEmail method
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        //generate tokens
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
