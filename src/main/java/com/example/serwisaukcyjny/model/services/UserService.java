package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.UserMenu.User;
import com.example.serwisaukcyjny.model.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;
    //  @Autowired
    // BCryptPasswordEncoder encoder;

    public User save(User user) {return userRepository.save(user);}

    public List<User> findAll(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }


    @GetMapping
    public ResponseEntity getUser() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        Optional<User> userFromOb = userRepository.findByUserName(user.getUserName());
        if (userFromOb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        User add = userRepository.save(user);
        // user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(add);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Long id) {
        userRepository.deleteById(id);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user, Long id) {
        Optional<User> current = userRepository.findById(id);
        if (user.getUserName() == null) {
            user.setUserName(current.get().getUserName());
        }
        if (user.getPassword() == null) {
            user.setPassword(current.get().getPassword());
        }
        if (user.getLocalization() == null) {
            user.setLocalization(current.get().getLocalization());
        } else {
            // user.setPassword(encoder.encode(user.getPassword()));
            user.getPassword();
        }
        return userRepository.save(user);
    }


}
