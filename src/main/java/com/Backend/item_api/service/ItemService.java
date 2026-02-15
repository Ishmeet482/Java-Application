package com.Backend.item_api.service;

import com.Backend.item_api.dto.ItemResponse;
import com.Backend.item_api.mapper.ItemMapper;
import org.springframework.stereotype.Service;

import com.Backend.item_api.dto.CreateItemRequest;
import com.Backend.item_api.exception.ItemNotFoundException;
import com.Backend.item_api.model.Item;
import com.Backend.item_api.repository.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponse addItem(CreateItemRequest request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setCategory(request.getCategory());
        item.setPrice(request.getPrice());

        Item newItem= itemRepository.save(item);

        return ItemMapper.toDTO(newItem);


    }

    public ItemResponse getItemById(Long id) {

        return itemRepository.findById(id).map(item -> ItemMapper.toDTO(item))
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public void clear() {
        itemRepository.deleteAll();
    }
}
