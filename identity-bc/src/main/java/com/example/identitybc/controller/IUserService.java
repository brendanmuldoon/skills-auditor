package com.example.identitybc.controller;

import com.example.identitybc.service.DTO.UserDTO;

import java.util.Optional;

public interface IUserService {


    Optional<UserDTO> authenticate(String username, String password);
}
