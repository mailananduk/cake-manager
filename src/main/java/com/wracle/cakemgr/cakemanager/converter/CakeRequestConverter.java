package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.springframework.beans.BeanUtils;

public class CakeRequestConverter {

    private CakeRequestConverter() {

    }

    public static CakeEntity convertCakeRequest(CakeDto cakeDto) {
        CakeEntity cakeEntity = new CakeEntity();
        BeanUtils.copyProperties(cakeDto, cakeEntity);
        return cakeEntity;
    }

}
