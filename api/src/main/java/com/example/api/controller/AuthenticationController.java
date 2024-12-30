package com.example.api.controller;

import com.example.api.domain.user.AppUser;
import com.example.api.domain.user.UserData;
import com.example.api.infra.security.JWTTokenData;
import com.example.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager autenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid UserData userData){
        Authentication authToken = new UsernamePasswordAuthenticationToken(userData.username(),userData.password());
        var authUser = autenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((AppUser)authUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
