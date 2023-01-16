package com.example.serwisaukcyjny.model;

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

    public Comment(String text, @NonNull Purchase purchase, @NonNull User user) {
        this.text = text;
        this.purchase = purchase;
        this.user = user;
    }
}
