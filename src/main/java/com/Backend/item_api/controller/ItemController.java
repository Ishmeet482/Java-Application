package com.Backend.item_api.controller;

import com.Backend.item_api.dto.ItemResponse;
import com.Backend.item_api.mapper.ItemMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.item_api.dto.CreateItemRequest;
import com.Backend.item_api.model.Item;
import com.Backend.item_api.service.ItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemResponse> addItem(@Valid @RequestBody CreateItemRequest request) {

        ItemResponse itemResponse = itemService.addItem(request);

        return ResponseEntity.ok().body(itemResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id) {

        ItemResponse itemResponse= itemService.getItemById(id);

        return ResponseEntity.ok().body(itemResponse);

    }
}
