package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Localization;
import com.example.serwisaukcyjny.model.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class AuctionMapper {

    public static Auction toEntity(CreateAuctionForm form, User user, Localization localization) {
        return new Auction(form.getTitle(),
                form.getDescription(),
                new BigDecimal(form.getMinimumPrice()),
                new BigDecimal(form.getBuyNowPrice()),
                form.isPromotion(),
                LocalDateTime.now(),
                LocalDateTime.of(2023, Month.JANUARY, 11, 11, 11),
                0,
                form.getCategory(),
                user,
                localization,
                form.getPhotos());
    }



}
