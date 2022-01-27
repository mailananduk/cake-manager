package com.wracle.cakemgr.cakemanager.service;

import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CakeService {
    public List<CakeDto> getAllCakes();
    public CakeDto getCake(@PathVariable Integer cakeId) throws BusinessException;
    public CakeDto addCake(@RequestBody CakeDto cakeDto);
}
