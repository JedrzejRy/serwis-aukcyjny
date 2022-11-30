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
    private String voivodeship;

    public Localization(int id, String address, String city, String voivodeship) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.voivodeship = voivodeship;
    }

