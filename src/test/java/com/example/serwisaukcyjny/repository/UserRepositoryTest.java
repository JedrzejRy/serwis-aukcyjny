package com.example.serwisaukcyjny.repository;

import com.example.serwisaukcyjny.SerwisAukcyjnyApplicationTests;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.Role;
import com.example.serwisaukcyjny.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryTest extends SerwisAukcyjnyApplicationTests {
    @Test
    void shouldCreateUser() {
        //given
        Localization localization = localizationRepository.save(new Localization("Bananowa", "Poznań", Localization.Voivodeship.WIELKOPOLSKIE));
        User user = new User("Jan@Gmail.com", "Kowalski", "jkowalski@gmail.com", LocalDateTime.now(), Role.USER, localization);

        //when
        User saved = userRepository.save(user);

        //then
        assertNotNull(saved);
        assertEquals("Kowalski", saved.getUserName());
        assertEquals("Jan@Gmail.com", saved.getLogin());
        assertEquals(Role.USER, saved.getRole());
    }


    @Test
    void shouldFindUserByUserName() {
        //given

        //when
        User user = userRepository.findByUserName("Tomasz").get();

        //then
        assertNotNull(user);
        assertEquals(user.getUserName(), "Tomasz");
    }

    @Test
    void shouldFindUserByLogin() {
        //given

        //when
        User user = userRepository.findByLogin("Marta@Gmail.com").get();

        //then
        assertNotNull(user);
        assertEquals(user.getLogin(), "Marta@Gmail.com");

    }

}
