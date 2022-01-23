package com.wracle.cakemgr.cakemanagerpoc.repository;

import com.wracle.cakemgr.cakemanagerpoc.repository.dao.CakeDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<CakeDao, Integer> {
}
