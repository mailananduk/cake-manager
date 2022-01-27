package com.wracle.cakemgr.cakemanager.converter;

import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CakeResponseConverterTest {

    @InjectMocks
    CakeResponseConverter cakeResponseConverter;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertCakeResponse() {
        CakeEntity cakeEntity = buildCakeEntity();
        Mockito.when(modelMapper.map(cakeEntity, CakeDto.class)).thenReturn(buildCakeDto());
        CakeDto responseCakeRestCakeDto = cakeResponseConverter.convertCakeResponse(cakeEntity);

        Assertions.assertNotNull(responseCakeRestCakeDto);
        Assertions.assertEquals(1, responseCakeRestCakeDto.getId());
        Assertions.assertEquals("Lemon Cake", responseCakeRestCakeDto.getName());
        Assertions.assertEquals("Taste good", responseCakeRestCakeDto.getDescription());
        Assertions.assertEquals("www.mycake.com/img1", responseCakeRestCakeDto.getImageUrl());
    }

    @Test
    public void testConvertCakeResponseForRequestList() {
        Iterable<CakeEntity> cakeEntityLst = Stream.of(buildCakeEntity()).collect(Collectors.toSet());
        List<CakeDto> responseCakeDtoList = cakeResponseConverter.convertCakeResponse(cakeEntityLst);

        Assertions.assertNotNull(responseCakeDtoList);
        Assertions.assertEquals(1, responseCakeDtoList.size());
    }

    private CakeEntity buildCakeEntity() {
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setId(1);
        cakeEntity.setName("Lemon Cake");
        cakeEntity.setDescription("Taste good");
        cakeEntity.setImageUrl("www.mycake.com/img1");
        return cakeEntity;
    }

    private CakeDto buildCakeDto() {
        CakeDto cakeDto = new CakeDto();
        cakeDto.setId(1);
        cakeDto.setName("Lemon Cake");
        cakeDto.setDescription("Taste good");
        cakeDto.setImageUrl("www.mycake.com/img1");
        return cakeDto;
    }

}
