package com.padapav.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {

    private Long id;
    private String name;
    private String category;
    private String description;
    private Long price;
    private boolean available;
    private boolean vegetarian;
}
