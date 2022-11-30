package com.example.serwisaukcyjny.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auctions")
@NoArgsConstructor
public class Aukcje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int auctionId;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "minimum_price")
    private BigDecimal minimumPrice;
    @Column(name = "buy_now_price")
    private BigDecimal buyNowPrice;
    @Column(name = "promotion")
    private boolean promotion;
    @Column(name = "date_of_issue")
    private LocalDateTime dateOfIssue;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "views")
    private int views;

    @ManyToOne
    Category category;
    @ManyToOne
    User user;
    @ManyToOne
    Localization localization;

    public Aukcje(int auctionId, String title, String description, BigDecimal minimumPrice, BigDecimal buyNowPrice, boolean promotion, LocalDateTime dateOfIssue, LocalDateTime endDate, int views, Category category, User user, Localization localization) {
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