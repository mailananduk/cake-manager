package com.wracle.cakemgr.cakemanager.io.repository;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CakeRepository extends CrudRepository<CakeEntity, Integer> {
    Optional<CakeEntity> findByName(String Name);
}
