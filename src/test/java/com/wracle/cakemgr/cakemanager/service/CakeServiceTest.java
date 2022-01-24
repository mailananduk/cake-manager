package com.wracle.cakemgr.cakemanager.service;

import com.wracle.cakemgr.cakemanager.converter.CakeRequestConverter;
import com.wracle.cakemgr.cakemanager.converter.CakeResponseConverter;
import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.service.impl.CakeServiceImpl;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class CakeServiceTest {

    @InjectMocks
    private CakeService cakeService = new CakeServiceImpl();

    @Mock
    private CakeRepository cakeRepository;

/*    @Mock
    CakeRequestConverter cakeRequestConverter;

    @Mock
    CakeResponseConverter cakeResponseConverter;*/

    @Test
    public void testGetAllCakes() {
        Iterable<CakeDao> cakeDaos = Stream.of(buildCakeDao()).collect(Collectors.toSet());;
        Mockito.when(cakeRepository.findAll()).thenReturn(cakeDaos);
        List<CakeRest> cakeRestList = cakeService.getAllCakes();

        Assertions.assertNotNull(cakeRestList);
        Assertions.assertEquals(1, cakeRestList.size());
    }

    @Test
    public void testGetCake() throws BusinessException {
        Optional<CakeDao> cakeDaoOptional = Optional.of(buildCakeDao());
        Mockito.when(cakeRepository.findById(2)).thenReturn(cakeDaoOptional);

        CakeRest cakeRest = cakeService.getCake(2);

        Assertions.assertNotNull(cakeRest);
        Assertions.assertTrue(cakeDaoOptional.isPresent());
    }

    @Test
    public void testGetCakeInvalidCakeId() {
        Mockito.when(cakeRepository.findById(20)).thenReturn(Optional.empty());

        BusinessException thrown = Assertions.assertThrows(BusinessException.class, () -> {
            cakeService.getCake(20);
        }, "Cake Id Not found");
    }

/*    @Test
    public void testaddCake() {

    }*/

    private CakeDao buildCakeDao() {
        CakeDao cakeDao = new CakeDao();
        cakeDao.setId(1);
        cakeDao.setName("Lemon Cake");
        cakeDao.setDescription("Taste good");
        cakeDao.setImageUrl("www.mycake.com/img1");
        return cakeDao;
    }
}
