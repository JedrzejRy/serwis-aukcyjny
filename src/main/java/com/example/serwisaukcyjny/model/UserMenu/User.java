package com.example.serwisaukcyjny.model.UserMenu;

import com.example.serwisaukcyjny.model.Localization;
import jakarta.persistence.*;
import lombok.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    private Type type;
    @NonNull
    @OneToOne
    private Localization localization;



}
