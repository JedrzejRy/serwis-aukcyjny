package com.example.serwisaukcyjny.model;

import com.example.serwisaukcyjny.model.UserMenu.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auctionId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal minimumPrice;
    @Column(nullable = false)
    private BigDecimal buyNowPrice;
    @Column(nullable = false)
    private boolean promotion;
    @Column(nullable = false)
    private LocalDateTime dateOfIssue;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private int views;

    @ManyToOne
    Category category;
    @ManyToOne
    User user;
    @ManyToOne
    Localization localization;

    public Auction(int auctionId, String title, String description, BigDecimal minimumPrice, BigDecimal buyNowPrice, boolean promotion, LocalDateTime dateOfIssue, LocalDateTime endDate, int views, Category category, User user, Localization localization) {
        this.auctionId = auctionId;
        this.title = title;
        this.description = description;
        this.minimumPrice = minimumPrice;
        this.buyNowPrice = buyNowPrice;
        this.promotion = promotion;
        this.dateOfIssue = dateOfIssue;
        this.endDate = endDate;
        this.views = views;
        this.category = category;
        this.user = user;
        this.localization = localization;
    }


}


/*tytuł
opis
zdjęcia (opcjonalnie)
kategoria
kwota minimalna
kwota Kup Teraz (znika, jeśli zacznie się licytacja)
promowana (można przyjąć, że konto premium, może promować np. 10 aukcji w miesiącu)
lokalizacja (odpowiada lokalizacji konta użytkownika)
data wystawienia
data zakończenia
ilość odwiedzin (wyświetleń strony aukcji)
*/