package com.example.serwisaukcyjny.model;

import com.example.serwisaukcyjny.model.UserMenu.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String text;

    @NonNull
    @OneToOne
    private Purchase purchase;

    @NonNull
    @OneToOne
    private User user;


    public Comment(int id, String comment, @NonNull Purchase purchase, @NonNull User user) {
        this.id = id;
        this.text = comment;
        this.purchase = purchase;
        this.user = user;
    }
}
