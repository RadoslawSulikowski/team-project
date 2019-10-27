package com.kodilla.ecommercee.domain.dao;

import com.kodilla.ecommercee.domain.Groups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GroupDao extends CrudRepository<Groups, Long> {
}
