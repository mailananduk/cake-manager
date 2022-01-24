package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CakeResponseConverterTest {

    @Test
    public void testConvertCakeResponse() {
        CakeDao cakeDao = buildCakeDao();
        CakeRest responseCakeRest = CakeResponseConverter.convertCakeResponse(cakeDao);

        Assertions.assertNotNull(responseCakeRest);
        Assertions.assertEquals(1, responseCakeRest.getId());
        Assertions.assertEquals("Lemon Cake", responseCakeRest.getName());
        Assertions.assertEquals("Taste good", responseCakeRest.getDescription());
        Assertions.assertEquals("www.mycake.com/img1", responseCakeRest.getImageUrl());
    }

    @Test
    public void testConvertCakeResponseForRequestList() {
        Iterable<CakeDao> cakeDaoLst = Stream.of(buildCakeDao()).collect(Collectors.toSet());
        List<CakeRest> responseCakeRestList = CakeResponseConverter.convertCakeResponse(cakeDaoLst);

        Assertions.assertNotNull(responseCakeRestList);
        Assertions.assertEquals(1, responseCakeRestList.size());
    }

    private CakeDao buildCakeDao() {
        CakeDao cakeDao = new CakeDao();
        cakeDao.setId(1);
        cakeDao.setName("Lemon Cake");
        cakeDao.setDescription("Taste good");
        cakeDao.setImageUrl("www.mycake.com/img1");
        return cakeDao;
    }
}
