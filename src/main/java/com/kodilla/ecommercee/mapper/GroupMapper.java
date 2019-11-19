package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMapper.class);

    @Autowired
    ProductMapper productMapper;

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName(),
                productMapper.mapToProductDtoList(group.getProducts()));
    }

    public Group mapToGroup(final GroupDto groupDto) {
        LOGGER.info("Mapping GroupDto to Group...");
        List<Product> products = new ArrayList<>();
        try {
            products = productMapper.mapToProductList(groupDto.getProducts());
            LOGGER.info("Mapping correct");
        } catch(ProductNotFoundException e) {
            LOGGER.error("Empty ArrayList has been set as products.", e);
        }
        return new Group(groupDto.getId(), groupDto.getName(), products);
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
