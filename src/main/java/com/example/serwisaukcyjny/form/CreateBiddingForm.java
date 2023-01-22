package com.example.serwisaukcyjny.form;


import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CreateBiddingForm {

    @NotBlank
    private User user;
    @NotBlank
    private Auction auction;
    @Positive
    private int price;


}
