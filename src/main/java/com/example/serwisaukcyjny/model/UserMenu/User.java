package com.example.serwisaukcyjny.model.UserMenu;

import com.example.serwisaukcyjny.model.Localization;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    public User( String login, String userName, String password, LocalDateTime startDate, Type type, Localization localization) {

        this.login = login;
        this.userName = userName;
        this.password = password;
        this.startDate = startDate;
        this.type = type;
        this.localization = localization;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    @Column

    private String userName;

    private String password;

    private LocalDateTime startDate;
    //  @Column
    //  private ImageIO logo;

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToOne
    private Localization localization;



}
