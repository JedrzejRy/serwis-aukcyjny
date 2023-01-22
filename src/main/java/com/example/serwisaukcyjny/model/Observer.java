package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(targetEntity = Auction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Auction> auctions;
    @ManyToOne
    private User user;

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }

    public Observer(Set<Auction> auctions, User user) {
        this.auctions = auctions;
        this.user = user;
    }
}
