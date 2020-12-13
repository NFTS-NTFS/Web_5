package com.example.demo.Dao;


import com.example.demo.Entity.linkman;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface linkmanRepository extends CrudRepository<linkman, Integer> {
    @Override
    List<linkman> findAll();
}
