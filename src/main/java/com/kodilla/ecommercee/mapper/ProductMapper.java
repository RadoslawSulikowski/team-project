package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    GroupRepository groupRepository;

    public List<ProductDto> mapToProductDtoList(final List<Product> products) {
        return products.stream()
                .map(n -> new ProductDto(n.getId(), n.getName(), n.getDescription(), n.getPrice(), n.getGroup().getId()))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtos) throws GroupNotFoundException {
        List<Product> products = productDtos.stream()
                .map(n -> new Product(n.getName(), n.getDescription(), n.getPrice()))
                .collect(Collectors.toList());
        for (int i=0; i<productDtos.size(); i++) {
            products.get(i).setId(productDtos.get(i).getId());
            products.get(i).setGroup(groupRepository.findById(productDtos.get(i).getGroupId()).orElseThrow(GroupNotFoundException::new));
        }
        return products;
    }
}
