package br.com.jfelipefaria.mapdata.api.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jfelipefaria.mapdata.api.dto.LoginRequest;
import br.com.jfelipefaria.mapdata.api.dto.LoginResponse;
import br.com.jfelipefaria.mapdata.api.security.JwtService;
import br.com.jfelipefaria.mapdata.api.security.UserDetailServiceImpl;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
            UserDetailServiceImpl userDetailsService,
            JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails user = userDetailsService.loadUserByUsername(request.username());
        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }
}
