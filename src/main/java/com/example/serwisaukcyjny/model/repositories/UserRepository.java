package com.example.serwisaukcyjny.model.repositories;

import com.example.serwisaukcyjny.model.UserMenu.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
    Optional<User> findByUserName(String username);
}
