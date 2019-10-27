package com.kodilla.ecommercee.domain.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<OrderDao, Long> {
}
