package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private int purchaseId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private String comment;

    public Comment(int purchaseId, int userId, String comment) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.comment = comment;
    }
}
