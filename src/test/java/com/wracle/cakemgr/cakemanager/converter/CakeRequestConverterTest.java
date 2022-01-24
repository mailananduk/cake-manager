package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CakeRequestConverterTest {
    @Test
    public void testconvertCakeRequest() {
        CakeRequestModel cakeRequestModel = buildCakeRequestModel();
        CakeDao responseCakeDao = CakeRequestConverter.convertCakeRequest(cakeRequestModel);

        Assertions.assertNotNull(responseCakeDao);
        Assertions.assertEquals("Lemon Cake", responseCakeDao.getName());
        Assertions.assertEquals("Taste good", responseCakeDao.getDescription());
        Assertions.assertEquals("www.mycake.com/img1", responseCakeDao.getImageUrl());
    }

    private CakeRequestModel buildCakeRequestModel() {
        CakeRequestModel cakeRequestModel = new CakeRequestModel();
        cakeRequestModel.setName("Lemon Cake");
        cakeRequestModel.setDescription("Taste good");
        cakeRequestModel.setImageUrl("www.mycake.com/img1");
        return cakeRequestModel;
    }


}
