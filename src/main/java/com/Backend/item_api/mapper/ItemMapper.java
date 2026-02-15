package com.Backend.item_api.mapper;

import com.Backend.item_api.dto.ItemResponse;
import com.Backend.item_api.model.Item;

public class ItemMapper {
    public static ItemResponse toDTO(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(item.getId());

        itemResponse.setName(item.getName());
        itemResponse.setPrice(item.getPrice());

        itemResponse.setDescription(item.getDescription());
        itemResponse.setCategory(item.getCategory());

        return itemResponse;
    }



}
