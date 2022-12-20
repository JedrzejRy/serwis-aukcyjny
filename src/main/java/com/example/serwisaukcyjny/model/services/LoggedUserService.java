package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.auth.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoggedUserService {
private final UserService userService;
public User findLoggedUser (){
    CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User loggedUser = userService.findByUserName(principal.getUsername()).get();
   return loggedUser;
}

}
