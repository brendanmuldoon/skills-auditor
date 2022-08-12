package com.example.identitybc.controller;

import com.example.identitybc.service.DTO.UserDTO;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class IdentityController {

    private IUserService userService;
    private JwtTokenUtil jwtTokenUtil;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody UserDetails command){
        Optional<UserDTO> result = userService.authenticate(command.getUsername(), command.getPassword());
        if(result.isPresent()){
            final String token = jwtTokenUtil.generateToken(result.get());
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.badRequest().body("Invalid details provided");
    }

}
