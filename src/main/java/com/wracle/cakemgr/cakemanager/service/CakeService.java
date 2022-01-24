package com.wracle.cakemgr.cakemanager.service;

import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CakeService {
    public List<CakeRest> getAllCakes();
    public CakeRest getCake(@PathVariable Integer cakeId) throws BusinessException;
    public ResponseEntity<CakeRest> addCake(@RequestBody CakeRequestModel cakeRequestModel);
}
