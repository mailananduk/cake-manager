package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import org.springframework.beans.BeanUtils;

public class CakeRequestConverter {
    private CakeRequestConverter() {

    }

    public static CakeDao convertCakeRequest(CakeRequestModel cakeRequestModel) {
        CakeDao cakeDao = new CakeDao();
        BeanUtils.copyProperties(cakeRequestModel, cakeDao);
        return cakeDao;
    }

}
