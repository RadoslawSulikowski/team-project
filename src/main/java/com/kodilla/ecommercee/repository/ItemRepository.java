package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Override
    List<Item> findAll();

    @Override
    Item save(Item product);

    @Override
    Optional<Item> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
