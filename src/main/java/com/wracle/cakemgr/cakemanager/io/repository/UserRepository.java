package com.wracle.cakemgr.cakemanager.io.repository;

import com.wracle.cakemgr.cakemanager.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailId(String emailId);
}
