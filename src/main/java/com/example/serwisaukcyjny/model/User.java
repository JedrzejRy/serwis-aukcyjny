package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    public User(String login, String username, String password, LocalDateTime startDate, Role role, Localization localization) {

        this.login = login;
        this.username = username;
        this.password = password;
        this.startDate = startDate;
        this.role = role;
        this.localization = localization;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    @Column

    private String username;

    private String password;

    private LocalDateTime startDate;
    //  @Column
    //  private ImageIO logo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Localization localization;



}
