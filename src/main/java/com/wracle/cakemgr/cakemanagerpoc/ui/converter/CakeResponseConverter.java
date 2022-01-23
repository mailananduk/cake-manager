package com.wracle.cakemgr.cakemanagerpoc.ui.converter;

import com.wracle.cakemgr.cakemanagerpoc.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanagerpoc.ui.model.response.CakeRest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CakeResponseConverter {

    private CakeResponseConverter() {

    }

    public static CakeRest convertCakeResponse(CakeDao cakeDao) {
        CakeRest cakeRest = new CakeRest();
        BeanUtils.copyProperties(cakeDao, cakeRest);
        return cakeRest;
    }

    public static List<CakeRest> convertCakeResponse(Iterable<CakeDao> cakeDaoLst) {
        return StreamSupport.stream(cakeDaoLst.spliterator(), false)
                .map((cakeDao) -> {
                    CakeRest cakeRest = new CakeRest();
                    BeanUtils.copyProperties(cakeDao, cakeRest);
                    return cakeRest;
                })
                .collect(Collectors.toList());
    }
}
