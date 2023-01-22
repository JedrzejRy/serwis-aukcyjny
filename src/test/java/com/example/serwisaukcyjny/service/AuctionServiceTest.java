package com.example.serwisaukcyjny.service;

import com.example.serwisaukcyjny.model.Auction;
import com.example.serwisaukcyjny.model.repositories.AuctionRepository;
import com.example.serwisaukcyjny.model.services.AuctionService;
import com.example.serwisaukcyjny.model.services.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuctionServiceTest {

    @Mock
    private AuctionRepository auctionRepository;

    @InjectMocks
    private AuctionService auctionService;

    @Test
    public void auctionService_CreateAuction_ReturnsAuction() {
        Auction auction = new Auction("Komputer", "Bardzo szybki komputer stacjonarny", BigDecimal.valueOf(250), BigDecimal.valueOf(2500), false, LocalDateTime.of(2023, Month.JANUARY, 27, 16, 30), LocalDateTime.of(2023, Month.JANUARY, 25, 16, 30), 0, null, null, null, "szpic_miniaturowy_pomeranian_1-1024x683.jpg");

        when(auctionRepository.save(Mockito.any(Auction.class))).thenReturn(auction);

        Auction savedAuction = auctionService.save(auction);

        Assertions.assertThat(savedAuction).isNotNull();

    }

}
