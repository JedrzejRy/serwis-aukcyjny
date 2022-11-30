package com.example.serwisaukcyjny.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int auctionId;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private BigDecimal price;

    public Bidding(int id, int auctionId, int userId, BigDecimal price) {
        this.id = id;
        this.auctionId = auctionId;
        this.userId = userId;
        this.price = price;
    }
}
