package com.wracle.cakemgr.cakemanager.repository;

import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CrudRepository<CakeDao, Integer> {
}
