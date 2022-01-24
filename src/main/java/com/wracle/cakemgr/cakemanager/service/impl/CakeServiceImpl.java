package com.wracle.cakemgr.cakemanager.service.impl;

import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanager.service.CakeService;
import com.wracle.cakemgr.cakemanager.converter.CakeRequestConverter;
import com.wracle.cakemgr.cakemanager.converter.CakeResponseConverter;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CakeServiceImpl implements CakeService {
    @Autowired
    private CakeRepository cakeRepository;

    @Override
    public List<CakeRest> getAllCakes() {
        Iterable<CakeDao> cakeDaos = cakeRepository.findAll();
        return CakeResponseConverter.convertCakeResponse(cakeDaos);
    }

    @Override
    public CakeRest getCake(Integer cakeId) throws BusinessException {
        Optional<CakeDao> cakeDaoOptional = cakeRepository.findById(cakeId);
        if(cakeDaoOptional.isPresent()) {
            return CakeResponseConverter.convertCakeResponse(cakeDaoOptional.get());
        }
        throw new BusinessException("Cake Id Not found");
    }

    @Override
    public ResponseEntity<CakeRest> addCake(CakeRequestModel cakeRequestModel) {
        CakeDao cakeDao = CakeRequestConverter.convertCakeRequest(cakeRequestModel);
        CakeDao savedCakeDao = cakeRepository.save(cakeDao);
        CakeRest cakeRest = CakeResponseConverter.convertCakeResponse(savedCakeDao);
        return new ResponseEntity(cakeRest, HttpStatus.CREATED);
    }
}
