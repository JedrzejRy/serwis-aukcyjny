package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    private final BCryptPasswordEncoder encoder;

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return new ArrayList<>(userRepository.findAll());
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByUserName (String userName){

        return userRepository.findByUsername(userName);

    }

    @DeleteMapping
    public void deleteUser(@RequestBody Long id) {
        userRepository.deleteById(id);
    }


}
