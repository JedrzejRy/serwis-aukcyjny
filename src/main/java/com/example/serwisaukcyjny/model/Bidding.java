package com.example.serwisaukcyjny.model;



import com.example.serwisaukcyjny.model.UserMenu.User;
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

    public Bidding(int auctionId, int userId, BigDecimal price) {
        this.price = price;
    }
}
