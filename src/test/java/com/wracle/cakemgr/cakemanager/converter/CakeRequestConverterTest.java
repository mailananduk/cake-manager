package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CakeRequestConverterTest {
    @Test
    public void testConvertCakeRequest() {
        CakeDto cakeDto = buildCakeDto();
        CakeEntity responseCakeEntity = CakeRequestConverter.convertCakeRequest(cakeDto);

        Assertions.assertNotNull(responseCakeEntity);
        Assertions.assertEquals("Lemon Cake", responseCakeEntity.getName());
        Assertions.assertEquals("Taste good", responseCakeEntity.getDescription());
        Assertions.assertEquals("www.mycake.com/img1", responseCakeEntity.getImageUrl());
    }

    private CakeDto buildCakeDto() {
        CakeDto cakeDto = new CakeDto();
        cakeDto.setName("Lemon Cake");
        cakeDto.setDescription("Taste good");
        cakeDto.setImageUrl("www.mycake.com/img1");
        return cakeDto;
    }


}
