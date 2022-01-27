package com.wracle.cakemgr.cakemanager.ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.io.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CakeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CakeRepository cakeRepository;

    @Test
    public void cakeAllLayers() throws Exception {
        CakeRequestModel cakeRequestModel = buildCakeRequestModel();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/cakes/")
                .content(objectMapper.writeValueAsString(cakeRequestModel))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", new String("Bearer: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmFuZEBhbmFuZC5jb20iLCJleHAiOjE2NDM0MTE4MTJ9.3wO8zZ0_zFmC-cE4SOXkGco6n2Fb99au1o2W9zXoBhxeZoC2jVzoPJfMHQSHc26VKaSWBF9aYOUL6IsSxdnqYQ"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lemon Cake 100"));

        Optional<CakeEntity> cakeEntityOptional  = cakeRepository.findByName("Lemon Cake 100");
        Assertions.assertTrue(cakeEntityOptional.isPresent());
        CakeEntity cakeEntity = cakeEntityOptional.get();
        Assertions.assertEquals("Taste good 100", cakeEntity.getDescription());
        Assertions.assertEquals("www.mycake.com/img100", cakeEntity.getImageUrl());
    }

    private CakeRequestModel buildCakeRequestModel() {
        CakeRequestModel cakeRequestModel = new CakeRequestModel();
        cakeRequestModel.setName("Lemon Cake 100");
        cakeRequestModel.setDescription("Taste good 100");
        cakeRequestModel.setImageUrl("www.mycake.com/img100");
        return cakeRequestModel;
    }
}
