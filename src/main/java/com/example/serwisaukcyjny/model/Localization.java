package com.example.serwisaukcyjny.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private Voivodeship voivodeship;

    public Localization(String address, String city, Voivodeship voivodeship) {
        this.address = address;
        this.city = city;
        this.voivodeship = voivodeship;
    }

    public enum Voivodeship {

MAZOWIECKIE, SLASKIE, POMORSKIE

    }

}


