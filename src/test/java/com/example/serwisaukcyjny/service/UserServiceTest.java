package com.example.serwisaukcyjny.service;

import com.example.serwisaukcyjny.form.CreateUserForm;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.Role;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.repositories.LocalizationRepository;
import com.example.serwisaukcyjny.model.repositories.UserRepository;
import com.example.serwisaukcyjny.model.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LocalizationRepository localizationRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void userService_CreateUser_ReturnsUser() {
        Localization localization = localizationRepository.save(new Localization("Bananowa", "Poznań", Localization.Voivodeship.WIELKOPOLSKIE));
        User user = new User("Jan@Gmail.com", "Kowalski", "jkowalski@gmail.com", LocalDateTime.now(), Role.USER, localization);
        BCryptPasswordEncoder encoder = Mockito.mock(BCryptPasswordEncoder.class);

        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User savedUser = userService.save(user);

        assertThat(savedUser).isNotNull();
    }
}
