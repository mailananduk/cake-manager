package com.wracle.cakemgr.cakemanagerpoc.ui.controller;

import com.wracle.cakemgr.cakemanagerpoc.exception.BusinessException;
import com.wracle.cakemgr.cakemanagerpoc.repository.CakeRepository;
import com.wracle.cakemgr.cakemanagerpoc.repository.dao.CakeDao;
import com.wracle.cakemgr.cakemanagerpoc.ui.converter.CakeResponseConverter;
import com.wracle.cakemgr.cakemanagerpoc.ui.model.response.CakeRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Access using http://localhost:8080/cakes
 */
@RestController
@RequestMapping("cakes")
public class CakeController {

    @Autowired
    private CakeRepository cakeRepository;

    @GetMapping("/")
    public List<CakeRest> getAllCakes() {
        Iterable<CakeDao> cakeDaos = cakeRepository.findAll();
        return CakeResponseConverter.convertCakeResponse(cakeDaos);
    }

    @GetMapping("/{cakeId}")
    public CakeRest getCake(@PathVariable Integer cakeId) throws Exception {
        Optional<CakeDao> cakeDaoOptional = cakeRepository.findById(cakeId);
        if(cakeDaoOptional.isPresent()) {
            return CakeResponseConverter.convertCakeResponse(cakeDaoOptional.get());
        }
        throw new BusinessException("Cake Id Not found");
    }

    @PostMapping("/")
    public ResponseEntity<CakeRest> addCake(@RequestBody CakeDao cakeDao) {
        CakeDao savedCakeDao = cakeRepository.save(cakeDao);
        CakeRest cakeRest = CakeResponseConverter.convertCakeResponse(savedCakeDao);
        return new ResponseEntity(cakeRest, HttpStatus.CREATED);
    }
}
