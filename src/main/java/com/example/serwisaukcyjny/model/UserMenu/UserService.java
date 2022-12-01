package com.example.serwisaukcyjny.model.UserMenu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;
  //  @Autowired
    // BCryptPasswordEncoder encoder;


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
    public User updateUser(@RequestBody User user, Long id){
        Optional<User> current = userRepository.findById(id);
        if (user.getUserName() == null){
            user.setUserName(current.get().getUserName());
        }
        if (user.getPassword() == null){
            user.setPassword(current.get().getPassword());
        }
        if (user.getLocalization() == null){
            user.setLocalization(current.get().getLocalization());
        } else {
           // user.setPassword(encoder.encode(user.getPassword()));
            user.getPassword();
        }
        return userRepository.save(user);
    }


}
