package com.example.identitybc.service;


import com.example.identitybc.controller.IUserService;
import com.example.identitybc.repo.AppUserJpa;
import com.example.identitybc.repo.UserRepository;
import com.example.identitybc.service.DTO.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;

    public Optional<UserDTO> authenticate(String username, String password){
        Optional<AppUserJpa> result = userRepository.findUserByUsernameAndPassword(username, password);
        return result.map(this::convertToDTO);
    }

    private UserDTO convertToDTO(AppUserJpa user){
        return new UserDTO(user.getUserUUID(),
                user.getUserName(),
                user.getPassword(),
                user.getFirstName(),
                user.getSurname(),
                user.getRole().toString());
    }
}


