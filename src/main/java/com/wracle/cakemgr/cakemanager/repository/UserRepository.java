package com.wracle.cakemgr.cakemanager.repository;

import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.repository.dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Integer> {
    Optional<UserDao> findByEmailId(String emailId);
}
