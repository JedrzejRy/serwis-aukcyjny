package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int auctionId;
    @Column(nullable = false)
    private int userId;

    public Observer(int auctionId, int userId) {
        this.auctionId = auctionId;
        this.userId = userId;
    }
}
