package com.padapav.catalog.controller;

import com.padapav.catalog.dto.MenuItemResponse;
import com.padapav.catalog.model.MenuItem;
import com.padapav.catalog.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    private final MenuItemService menuItemService;

    public MenuController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/menu")
    public List<MenuItemResponse> getMenu() {
        return menuItemService.getAllMenuItems().stream()
            .map(menuItemService::toResponse)
            .toList();
    }

    @GetMapping("/menu/{id}")
    public MenuItemResponse getMenuItem(@PathVariable Long id) {
        return menuItemService.toResponse(menuItemService.getMenuItemById(id));
    }

    @PostMapping("/admin/menu-items")
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItem menuItem) {
        MenuItem saved = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.toResponse(saved));
    }
}
