package com.example.serwisaukcyjny.form;

import com.example.serwisaukcyjny.form.validation.constrains.AuctionDuration;
import com.example.serwisaukcyjny.form.validation.constrains.AuctionPastDate;
import com.example.serwisaukcyjny.model.Category;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AuctionDuration(message = "Aukcja może trwać maksymalnie tydzień")
@AuctionPastDate(message = "Aukcja musi trwać minimum jeden dzień")
public class CreateAuctionForm {

    @NotBlank (message = "aukcja musi mieć tytuł")
    private String title;
    private String description;
    @Positive(message = "Wartość musi być dodatnia")
    private int minimumPrice;
    @Positive(message = "Wartość musi być dodatnia")
    private int buyNowPrice;
    private boolean promotion;
    private LocalDateTime dateOfIssue;
    @Future
    private LocalDateTime endDate;
    private int views;
    @NonNull
    private Category category;
    @Length (max = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || title == null) return null;

        return "/photos/" + photos;
    }

}
