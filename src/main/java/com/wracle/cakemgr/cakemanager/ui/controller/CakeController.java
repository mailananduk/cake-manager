package com.wracle.cakemgr.cakemanager.ui.controller;

import com.wracle.cakemgr.cakemanager.service.CakeService;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Access using http://localhost:8080/cakes
 */
@RestController
@RequestMapping("cakes")
public class CakeController {

    @Autowired
    CakeService cakeService;

    @GetMapping("/")
    public List<CakeRest> getAllCakes() {
        List<CakeDto> cakeDtoLst = cakeService.getAllCakes();
        List<CakeRest> resultCakeRestLst = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(CakeDto cakeDto: cakeDtoLst) {
            CakeRest cakeRest = modelMapper.map(cakeDto, CakeRest.class);
            resultCakeRestLst.add(cakeRest);
        }
        return resultCakeRestLst;
    }

    @GetMapping("/{cakeId}")
    public CakeRest getCake(@PathVariable Integer cakeId) throws Exception {
        CakeDto cakeDto = cakeService.getCake(cakeId);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cakeDto, CakeRest.class);
    }

    @PostMapping("/")
    public ResponseEntity<CakeRest> addCake(@RequestBody CakeRequestModel cakeRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        CakeDto cakeDto = new CakeDto();
        cakeDto = new ModelMapper().map(cakeRequestModel, CakeDto.class);

        CakeDto createdCake = cakeService.addCake(cakeDto);

        CakeRest resultCakeRest = new CakeRest();
        resultCakeRest = new ModelMapper().map(createdCake, CakeRest.class);
        return new ResponseEntity(resultCakeRest, HttpStatus.CREATED);

    }
}
