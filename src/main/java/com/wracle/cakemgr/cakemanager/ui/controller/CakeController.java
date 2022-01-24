package com.wracle.cakemgr.cakemanager.ui.controller;

import com.wracle.cakemgr.cakemanager.service.CakeService;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return cakeService.getAllCakes();
    }

    @GetMapping("/{cakeId}")
    public CakeRest getCake(@PathVariable Integer cakeId) throws Exception {
        return cakeService.getCake(cakeId);
    }

    @PostMapping("/")
    public ResponseEntity<CakeRest> addCake(@RequestBody CakeRequestModel cakeRequestModel) {
        return cakeService.addCake(cakeRequestModel);
    }
}
