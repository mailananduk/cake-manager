package com.wracle.cakemgr.cakemanager.ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.io.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.ui.model.request.CakeRequestModel;
import com.wracle.cakemgr.cakemanager.ui.model.request.UsernamePasswordLoginRequestModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
        //login to get Authorization (in response header)
        UsernamePasswordLoginRequestModel loginUser = buildLoginUser();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/login")
                        .content(objectMapper.writeValueAsString(loginUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        String authorization = mvcResult.getResponse().getHeader("Authorization");

        //use Authorization when calling create cake
        CakeRequestModel cakeRequestModel = buildCakeRequestModel();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cakes/")
                        .content(objectMapper.writeValueAsString(cakeRequestModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authorization)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Lemon Cake 100"));

        Optional<CakeEntity> cakeEntityOptional  = cakeRepository.findByName("Lemon Cake 100");
        Assertions.assertTrue(cakeEntityOptional.isPresent());
        CakeEntity cakeEntity = cakeEntityOptional.get();
        Assertions.assertEquals("Taste good 100", cakeEntity.getDescription());
        Assertions.assertEquals("www.mycake.com/img100", cakeEntity.getImageUrl());
    }

    @Test
    public void cakeAllLayersWithoutAuthorization() throws Exception {
        CakeRequestModel cakeRequestModel = buildCakeRequestModel();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cakes/")
                        .content(objectMapper.writeValueAsString(cakeRequestModel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    private UsernamePasswordLoginRequestModel buildLoginUser() {
        UsernamePasswordLoginRequestModel loginUser = new UsernamePasswordLoginRequestModel();
        loginUser.setEmailId("anand@anand.com");
        loginUser.setPassword("admin");
        return loginUser;
    }

    private CakeRequestModel buildCakeRequestModel() {
        CakeRequestModel cakeRequestModel = new CakeRequestModel();
        cakeRequestModel.setName("Lemon Cake 100");
        cakeRequestModel.setDescription("Taste good 100");
        cakeRequestModel.setImageUrl("www.mycake.com/img100");
        return cakeRequestModel;
    }

}
