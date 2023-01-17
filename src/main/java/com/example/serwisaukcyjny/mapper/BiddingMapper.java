package com.example.serwisaukcyjny.mapper;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.Bidding;
import com.example.serwisaukcyjny.model.User;

import java.math.BigDecimal;

public class BiddingMapper {
    public static Bidding ToEntity(User user, Auction auction, BigDecimal price) {
        return new Bidding(price,auction,user);
    }
}
