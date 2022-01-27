package com.wracle.cakemgr.cakemanager.service.impl;

import com.wracle.cakemgr.cakemanager.converter.CakeRequestConverter;
import com.wracle.cakemgr.cakemanager.converter.CakeResponseConverter;
import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.io.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.service.CakeService;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CakeServiceImpl implements CakeService {
    @Autowired
    private CakeRepository cakeRepository;
    @Autowired
    CakeResponseConverter cakeResponseConverter;

    @Override
    public List<CakeDto> getAllCakes() {
        Iterable<CakeEntity> cakeEntities = cakeRepository.findAll();
        return cakeResponseConverter.convertCakeResponse(cakeEntities);
    }

    @Override
    public CakeDto getCake(Integer cakeId) throws BusinessException {
        Optional<CakeEntity> cakeEntityOptional = cakeRepository.findById(cakeId);
        if(cakeEntityOptional.isPresent()) {
            return cakeResponseConverter.convertCakeResponse(cakeEntityOptional.get());
        }
        throw new BusinessException("Cake Id Not found");
    }

    @Override
    public CakeDto addCake(CakeDto cakeDto) {

        CakeEntity cakeEntity = CakeRequestConverter.convertCakeRequest(cakeDto);

        CakeEntity savedCakeEntity = cakeRepository.save(cakeEntity);
        return cakeResponseConverter.convertCakeResponse(savedCakeEntity);
    }
}
