package com.padapav.catalog.service;

import com.padapav.catalog.dto.MenuItemResponse;
import com.padapav.catalog.model.MenuItem;
import com.padapav.catalog.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Menu item not found: " + id));
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        if (menuItem.getName() == null || menuItem.getName().isBlank()) {
            throw new IllegalArgumentException("Menu item name is required");
        }
        if (menuItem.getPrice() == null || menuItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Menu item price must be greater than zero");
        }
        return menuItemRepository.save(menuItem);
    }

    public MenuItemResponse toResponse(MenuItem menuItem) {
        return new MenuItemResponse(
            menuItem.getId(),
            menuItem.getName(),
            menuItem.getCategory(),
            menuItem.getDescription(),
            menuItem.getPrice(),
            menuItem.isAvailable(),
            menuItem.isVegetarian()
        );
    }
}
