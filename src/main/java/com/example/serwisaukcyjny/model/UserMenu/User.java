package com.example.serwisaukcyjny.model.UserMenu;

import com.example.serwisaukcyjny.model.Localization;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class User {
    public User(String login, String userName, String password, String startDate, Type type){
        this.login = login;
        this.userName = userName;
        this.password = password;
        this.startDate = LocalDate.parse(startDate);
        this.type = type;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String login;
    @Column
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private LocalDate startDate;
  //  @Column
  //  private ImageIO logo;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Type type;
    @NonNull
    @OneToOne
    private Localization localization;



}
