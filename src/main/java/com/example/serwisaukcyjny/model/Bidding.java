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
    private BigDecimal price;

    @OneToOne
    Auction auctionID;

    @OneToOne
    User userId;

    public Bidding(BigDecimal price, Auction auctionID, User userId) {
        this.price = price;
        this.auctionID = auctionID;
        this.userId = userId;
    }
}
