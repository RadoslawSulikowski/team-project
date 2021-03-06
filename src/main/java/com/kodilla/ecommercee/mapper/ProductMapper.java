package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    GroupRepository groupRepository;

    public Product mapToProduct(final ProductDto productDto) throws GroupNotFoundException {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                groupRepository.findById(productDto.getGroupId()).orElseThrow(GroupNotFoundException::new));
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup().getId());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(n -> new ProductDto(n.getId(), n.getName(), n.getDescription(), n.getPrice(), n.getGroup().getId()))
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtos) throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        for (ProductDto p : productDtos) {
            if (productRepository.findById(p.getId()).isPresent()) {
                products.add(productRepository.findById(p.getId()).get());
            } else {
                throw new ProductNotFoundException();
            }
        }
        return products;
    }
}
