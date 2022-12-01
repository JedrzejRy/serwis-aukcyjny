package com.example.serwisaukcyjny.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String categoryName;

    public Category(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
