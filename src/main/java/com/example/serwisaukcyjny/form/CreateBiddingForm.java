package com.example.serwisaukcyjny.form;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.User;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CreateBiddingForm {

    private User user;
    private Auction auction;
    private int price;


}
