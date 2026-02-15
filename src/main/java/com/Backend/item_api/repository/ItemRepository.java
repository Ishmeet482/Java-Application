package com.Backend.item_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Backend.item_api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
