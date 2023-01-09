package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.form.CreateAuctionForm;
import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.User;
import com.example.serwisaukcyjny.model.services.LoggedUserService;
import com.example.serwisaukcyjny.model.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class AuctionMapper {

    private final UserService loggedUserService;


    public static Auction toEntity(CreateAuctionForm form, User user) {
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

                null);
    }



}
