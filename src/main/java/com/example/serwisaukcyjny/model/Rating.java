package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int purchaseId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RatingScore buyerRatingScore;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RatingScore sellerRatingScore;

    public Rating(int id, int purchaseId, RatingScore buyerRatingScore, RatingScore sellerRatingScore) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.buyerRatingScore = buyerRatingScore;
        this.sellerRatingScore = sellerRatingScore;
    }
}
