package com.wracle.cakemgr.cakemanager.io.repository;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CakeRepositoryTest {
    @Autowired
    CakeRepository cakeRepository;

    @Test
    public void testFindAll() {
        Iterable<CakeEntity> cakeEntities = cakeRepository.findAll();

        Assertions.assertNotNull(cakeEntities);
    }

    @Test
    public void testFindById() {
        Optional<CakeEntity> cakeEntityOptional = cakeRepository.findById(2);

        Assertions.assertNotNull(cakeEntityOptional);
        Assertions.assertTrue(cakeEntityOptional.isPresent());
    }

    @Test
    public void testSave() {
        CakeEntity cakeEntity = buildCakeEntity();
        cakeRepository.save(cakeEntity);

        Optional<CakeEntity> cakeEntityOptional = cakeRepository.findById(6);

        Assertions.assertNotNull(cakeEntityOptional);
        Assertions.assertTrue(cakeEntityOptional.isPresent());
        Assertions.assertEquals(6, cakeEntityOptional.get().getId());
    }

    private CakeEntity buildCakeEntity() {
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setName("Lemon Cake10");
        cakeEntity.setDescription("Taste good10");
        cakeEntity.setImageUrl("www.mycake.com/img10");
        return cakeEntity;
    }
}
