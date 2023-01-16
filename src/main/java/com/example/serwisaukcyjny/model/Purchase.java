package com.example.serwisaukcyjny.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @OneToOne
    private Auction auction;
    @NonNull
    @OneToOne
    private User user;
    @Column(nullable = false)
    private BigDecimal price;

    public Purchase(@NonNull Auction auction, @NonNull User user, BigDecimal price) {
        this.auction = auction;
        this.user = user;
        this.price = price;
    }
}
