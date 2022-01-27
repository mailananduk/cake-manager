package com.wracle.cakemgr.cakemanager.ui.controller;

import com.wracle.cakemgr.cakemanager.service.CakeService;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import com.wracle.cakemgr.cakemanager.ui.model.response.CakeRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CakeControllerTest {

    @InjectMocks
    CakeController cakeController;

    @Mock
    CakeService cakeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCakes() {
        List<CakeDto> cakeDtoLst = buildCakeDtoLst();
        Mockito.when(cakeService.getAllCakes()).thenReturn(cakeDtoLst);
        List<CakeRest> allCakes = cakeController.getAllCakes();

        Assertions.assertNotNull(allCakes);
        Assertions.assertEquals(1, allCakes.size());
    }

    private CakeDto buildCakeDto() {
        CakeDto cakeDto = new CakeDto();
        cakeDto.setId(1);
        cakeDto.setName("Lemon Cake");
        cakeDto.setDescription("Taste good");
        cakeDto.setImageUrl("www.mycake.com/img1");
        return cakeDto;
    }

    private List<CakeDto> buildCakeDtoLst() {
        List<CakeDto> resultLst = new ArrayList<>();
        CakeDto cakeDto = buildCakeDto();
        resultLst.add(cakeDto);
        return resultLst;
    }
}
