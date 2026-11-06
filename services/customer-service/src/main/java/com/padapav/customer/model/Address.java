package com.padapav.customer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String label;

    @NotBlank
    private String street;

    @NotBlank
    private String area;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postcode;

    private String landmark;
}
