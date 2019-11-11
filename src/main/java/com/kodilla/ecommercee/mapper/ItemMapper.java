package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.ItemDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;

    public Item mapToItem(final ItemDto itemDto) throws ProductNotFoundException {
        return new Item(
                itemDto.getId(),
                itemDto.getQuantity(),
                productRepository.findById(itemDto.getProductId()).orElseThrow(ProductNotFoundException::new));
    }

    public ItemDto mapToItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getQuantity(),
                item.getProduct().getId()
        );
    }

    public List<Item> mapToItemsList(List<ItemDto> itemsDto) throws ProductNotFoundException {
        List<Item> items = new ArrayList<>();
        for (ItemDto p : itemsDto) {
            if (productRepository.findById(p.getProductId()).isPresent()) {
                items.add(new Item(p.getId(), p.getQuantity(), productRepository.findById(p.getProductId()).get()));
            } else {
                throw new ProductNotFoundException();
            }
        }
        return items;
    }

    public List<ItemDto> mapToItemsDtoList(List<Item> items) {
        return items.stream()
                .map(i -> new ItemDto(i.getId(), i.getQuantity(), i.getProduct().getId()))
                .collect(Collectors.toList());
    }
}
