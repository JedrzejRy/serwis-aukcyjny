package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.model.Category;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateAuctionForm {

    private String title;
    private String description;
    @Positive(message = "Wartość musi być dodatnia")
    private int minimumPrice;
    @Positive(message = "Wartość musi być dodatnia")
    private int buyNowPrice;
    private boolean promotion;
    private LocalDateTime dateOfIssue;
    private LocalDateTime endDate;
    private int views;
    private Category category;

}
