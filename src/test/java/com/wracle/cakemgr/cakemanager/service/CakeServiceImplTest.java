package com.wracle.cakemgr.cakemanager.service;

import com.wracle.cakemgr.cakemanager.converter.CakeRequestConverter;
import com.wracle.cakemgr.cakemanager.converter.CakeResponseConverter;
import com.wracle.cakemgr.cakemanager.exception.BusinessException;
import com.wracle.cakemgr.cakemanager.io.entity.CakeEntity;
import com.wracle.cakemgr.cakemanager.io.repository.CakeRepository;
import com.wracle.cakemgr.cakemanager.service.impl.CakeServiceImpl;
import com.wracle.cakemgr.cakemanager.shared.dto.CakeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

//@SpringBootTest
public class CakeServiceImplTest {

    @InjectMocks
    private CakeServiceImpl cakeService;

    @Mock
    private CakeRepository cakeRepository;

//    @Mock
//    CakeRequestConverter cakeRequestConverter;

    @Mock
    CakeResponseConverter cakeResponseConverter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCakes() {

        Iterable<CakeEntity> cakeEntities = Stream.of(buildCakeEntity()).collect(Collectors.toSet());;
        when(cakeRepository.findAll()).thenReturn(cakeEntities);
        when(cakeResponseConverter.convertCakeResponse(cakeEntities))
                .thenReturn(buildCakeDtoLst());

        List<CakeDto> cakeDtoList = cakeService.getAllCakes();

        Assertions.assertNotNull(cakeDtoList);
        Assertions.assertEquals(1, cakeDtoList.size());
    }

    @Test
    public void testGetCake() throws BusinessException {
        Optional<CakeEntity> cakeDaoOptional = Optional.of(buildCakeEntity());
        when(cakeRepository.findById(2)).thenReturn(cakeDaoOptional);
        when(cakeResponseConverter.convertCakeResponse(any(CakeEntity.class)))
                .thenReturn(buildCakeDto());

        CakeDto cakeDto = cakeService.getCake(2);

        Assertions.assertNotNull(cakeDto);
        Assertions.assertTrue(cakeDaoOptional.isPresent());
    }

    @Test
    public void testGetCakeInvalidCakeId() {
        when(cakeRepository.findById(20)).thenReturn(Optional.empty());

        BusinessException thrown = Assertions.assertThrows(BusinessException.class, () -> {
            cakeService.getCake(20);
        }, "Cake Id Not found");
    }

    @Test
    public void testaddCake() {
        try(MockedStatic<CakeRequestConverter> mockReqConverter = Mockito.mockStatic(CakeRequestConverter.class)) {
            mockReqConverter.when(() -> CakeRequestConverter.convertCakeRequest(any(CakeDto.class)))
                    .thenReturn(new CakeEntity());
            when(cakeRepository.save(any(CakeEntity.class)))
                    .thenReturn(new CakeEntity());
            when(cakeResponseConverter.convertCakeResponse(any(CakeEntity.class)))
                    .thenReturn(new CakeDto());

            CakeDto cakeDto = buildCakeDto();
            CakeDto createdCake = cakeService.addCake(cakeDto);

            Assertions.assertNotNull(createdCake);
            mockReqConverter.verify(() -> CakeRequestConverter.convertCakeRequest(any(CakeDto.class)),
                    times(1));
            Mockito.verify(cakeRepository,
                    times(1)).save((any(CakeEntity.class)));
            Mockito.verify(cakeResponseConverter,
                    times(1)).convertCakeResponse((any(CakeEntity.class)));
        }

    }

    private CakeEntity buildCakeEntity() {
        CakeEntity cakeEntity = new CakeEntity();
        cakeEntity.setId(1);
        cakeEntity.setName("Lemon Cake");
        cakeEntity.setDescription("Taste good");
        cakeEntity.setImageUrl("www.mycake.com/img1");
        return cakeEntity;
    }

    private List<CakeEntity> buildCakeEntityLst() {
        List<CakeEntity> resultLst = new ArrayList<>();
        CakeEntity cakeEntity = buildCakeEntity();
        resultLst.add(cakeEntity);
        return resultLst;
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

