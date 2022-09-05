package com.example.identitybc.service;


import com.example.identitybc.controller.IUserService;
import com.example.identitybc.repo.AppUserJpa;
import com.example.identitybc.repo.UserRepository;
import com.example.identitybc.service.DTO.UserDTO;
import com.example.identitybc.service.events.IdentityUpdateUsernameAndPasswordEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.Message;
import javax.jms.TextMessage;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@EnableJms
public class UserService implements IUserService {
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    public Optional<UserDTO> authenticate(String username, String password){
        Optional<AppUserJpa> result = userRepository.findUserByUsernameAndPassword(username, password);
        return result.map(this::convertToDTO);
    }

    @JmsListener(destination = "IDENTITY.UPDATE.QUEUE")
    public void updateUserListener(Message message) {

        LOG.info("Received message from IDENTITY.UPDATE.QUEUE");

        try {

            if (message instanceof TextMessage) {

                String messageBody = ((TextMessage) message).getText();

                IdentityUpdateUsernameAndPasswordEvent event = objectMapper.readValue(messageBody, IdentityUpdateUsernameAndPasswordEvent.class);

                Optional<AppUserJpa> userJpa = userRepository.findById(event.getId());

                if (userJpa.isPresent()) {

                    userJpa.get().update(event);

                    userRepository.save(userJpa.get());

                }
          }

        } catch (Exception e) {

            LOG.error(e.getMessage());

        }
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


