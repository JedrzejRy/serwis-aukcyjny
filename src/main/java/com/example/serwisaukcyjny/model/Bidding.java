package com.example.serwisaukcyjny.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Bidding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private BigDecimal price;

    @OneToOne
    Auction auction;

    @OneToOne
    User user;

    public Bidding(BigDecimal price, Auction auction, User user) {
        this.price = price;
        this.auction = auction;
        this.user = user;
    }

    public Purchase toPurchase(){
        return new Purchase(auction,user,price);
    }
}
