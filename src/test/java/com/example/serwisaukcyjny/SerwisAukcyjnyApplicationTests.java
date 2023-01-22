package com.example.serwisaukcyjny;

import com.example.serwisaukcyjny.model.*;
import com.example.serwisaukcyjny.model.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class SerwisAukcyjnyApplicationTests {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected LocalizationRepository localizationRepository;
    @Autowired
    protected PurchaseRepository purchaseRepository;
    @Autowired
    protected ObserverRepository observerRepository;
    @Autowired
    protected AuctionRepository auctionRepository;
    @Autowired
    protected CategoryRepository categoryRepository;

    @Autowired
    protected BiddigRepository biddigRepository;
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper mapper;

    @BeforeEach
    void beforeEach() {
        Localization localization = localizationRepository.save(new Localization("Nowa", "Warszawa", Localization.Voivodeship.DOLNOŚLĄSKIE));
        Category category = categoryRepository.save(new Category("Komputery"));
        Category category2 = categoryRepository.save(new Category("Meble"));
        User tomasz = userRepository.save(new User("Tomasz@Gmail.com", "Tomasz", "Kołodziej", LocalDateTime.now(), Role.USER, localization));
        User marta = userRepository.save(new User("Marta@Gmail.com", "Marta", "Czarodziej", LocalDateTime.now(), Role.USER, localization));
        User ania = userRepository.save(new User("Ania@Gmail.com", "Ania", "1234567", LocalDateTime.now(), Role.USER, localization));
        User zosia = userRepository.save(new User("Zosia@Gmail.com", "Zosia", "PaniSamolot", LocalDateTime.now(), Role.USER, localization));

        Auction zosiaAuction = auctionRepository.save(new Auction("Komputer", "Bardzo szybki komputer stacjonarny", BigDecimal.valueOf(250), BigDecimal.valueOf(2500), false, LocalDateTime.of(2023, Month.JANUARY, 27, 16, 30), LocalDateTime.of(2023, Month.JANUARY, 25, 16, 30), 0, category, zosia, zosia.getLocalization(), "szpic_miniaturowy_pomeranian_1-1024x683.jpg"));
        Auction zosiaAuction2 = auctionRepository.save(new Auction("Komputer", "Jeszcze bardziej szybki komputer stacjonarny", BigDecimal.valueOf(400), BigDecimal.valueOf(3000), false, LocalDateTime.of(2023, Month.JANUARY, 27, 16, 30), LocalDateTime.of(2023, Month.JANUARY, 25, 16, 30), 0, category, zosia, zosia.getLocalization(), "szpic_miniaturowy_pomeranian_1-1024x683.jpg"));
        Auction martaAuction = auctionRepository.save(new Auction("Kanapa", "Wygodna Kanapa", BigDecimal.valueOf(250), BigDecimal.valueOf(2500), false, LocalDateTime.of(2023, Month.JANUARY, 27, 16, 30), LocalDateTime.of(2023, Month.JANUARY, 25, 16, 30), 0, category2, marta, marta.getLocalization(), "szpic_miniaturowy_pomeranian_1-1024x683.jpg"));

        Purchase purchase = purchaseRepository.save(new Purchase(zosiaAuction, ania, zosiaAuction.getBuyNowPrice()));

    }

    @AfterEach
    void afterEach() {
        purchaseRepository.deleteAll();
        observerRepository.deleteAll();
        biddigRepository.deleteAll();
        auctionRepository.deleteAll();
        userRepository.deleteAll();
        localizationRepository.deleteAll();
        categoryRepository.deleteAll();

    }

}
