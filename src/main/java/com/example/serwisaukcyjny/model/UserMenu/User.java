package com.example.serwisaukcyjny.model.UserMenu;

import com.example.serwisaukcyjny.model.Localization;
import jakarta.persistence.*;
import lombok.*;

import javax.imageio.ImageIO;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String login;
    @Column(name = "user_name")
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private LocalDate startDate;
    @Column
    private ImageIO logo;
    @NonNull
    private Type type;
    @NonNull
    private Localization localization;



}
