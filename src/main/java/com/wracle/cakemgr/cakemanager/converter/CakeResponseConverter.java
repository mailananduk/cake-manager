package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CakeResponseConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CakeDto convertCakeResponse(CakeEntity cakeEntity) {
        return modelMapper.map(cakeEntity, CakeDto.class);
    }

    public List<CakeDto> convertCakeResponse(Iterable<CakeEntity> cakeEntityLst) {
        return StreamSupport.stream(cakeEntityLst.spliterator(), false)
                .map(cakeEntity -> convertCakeResponse(cakeEntity))
                .collect(Collectors.toList());
    }
}
