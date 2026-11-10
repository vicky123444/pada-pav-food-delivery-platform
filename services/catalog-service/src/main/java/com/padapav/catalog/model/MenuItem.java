package com.padapav.catalog.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotBlank private String category;
    @NotBlank private String description;
    @NotNull @Positive private Long price;
    private boolean available = true;
    private boolean vegetarian = true;
}
