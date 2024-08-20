package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.config.StyleByProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.CompanyEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.ProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.RailwayEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeedRepository;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeedServiceImplTest {

    @Mock
    private IDeedRepository deedRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DeedServiceImpl deedService;

    @BeforeEach
    void setUp() {
        deedService = new DeedServiceImpl(deedRepository, modelMapper);
    }

    @Test
    void testGetDeedByBoxId() {
        int boxId = 1;
        DeedEntity deedEntity = new DeedEntity();
        deedEntity.setDeedType(DeedType.PROVINCE);
        deedEntity.setIdDeed(1);
        deedEntity.setDeedName("Test Deed");
        deedEntity.setPurchaseValue(100);
        deedEntity.setMortgageValue(50);

        ProvinceEntity provinceEntity = new ProvinceEntity();
        provinceEntity.setRent(10);
        provinceEntity.setRentRanch(20);
        provinceEntity.setRent1Farm(30);
        provinceEntity.setRent2Farms(40);
        provinceEntity.setRent3Farms(50);
        provinceEntity.setRent4Farms(60);

        Province province = new Province();

        when(deedRepository.getDeedByBoxId(boxId)).thenReturn(deedEntity);
        when(deedRepository.getProvinceByDeedId(deedEntity.getIdDeed())).thenReturn(provinceEntity);
        when(modelMapper.map(provinceEntity, Province.class)).thenReturn(province);

        AbstractDeed result = deedService.getDeedByBoxId(boxId);

        assertEquals(province, result);
        assertEquals(1, province.getIdDeed());
        assertEquals("Test Deed", province.getName());
        assertEquals(100, province.getPurchaseValue());
        assertEquals(50, province.getMortgageValue());
        assertEquals(10, province.getRentValue());
        assertEquals(20, province.getRent_ranch());
        assertEquals(30, province.getRent_1_farm());
        assertEquals(40, province.getRent_2_farm());
        assertEquals(50, province.getRent_3_farm());
        assertEquals(60, province.getRent_4_farm());
    }

    @Test
    void testGetCompanies() {
        List<CompanyEntity> companyEntities = new ArrayList<>();
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntities.add(companyEntity);

        when(deedRepository.getCompanies()).thenReturn(companyEntities);
        when(modelMapper.map(companyEntity, Company.class)).thenReturn(new Company());

        List<Company> companies = deedService.getCompanies();

        assertEquals(1, companies.size());
    }

    @Test
    void testGetRailways() {
        List<RailwayEntity> railwayEntities = new ArrayList<>();
        RailwayEntity railwayEntity = new RailwayEntity();
        railwayEntities.add(railwayEntity);

        when(deedRepository.getRailways()).thenReturn(railwayEntities);
        when(modelMapper.map(railwayEntity, Railway.class)).thenReturn(new Railway());

        List<Railway> railways = deedService.getRailways();

        assertEquals(1, railways.size());
    }

    @Test
    void testGetProvincesByStyleGame() {
        GameStyle styleGame = new GameStyle();
        styleGame.setIdStyleGame(1);

        List<StyleByProvinceEntity> provinceEntities = new ArrayList<>();
        StyleByProvinceEntity styleByProvinceEntity = new StyleByProvinceEntity();
        ProvinceEntity provinceEntity = new ProvinceEntity();
        styleByProvinceEntity.setIdProvince(provinceEntity);
        provinceEntities.add(styleByProvinceEntity);

        when(deedRepository.getProvincesByStyle(styleGame.getIdStyleGame())).thenReturn(provinceEntities);
        when(modelMapper.map(provinceEntity, Province.class)).thenReturn(new Province());

        List<Province> provinces = deedService.getProvincesByStyleGame(styleGame);

        assertEquals(1, provinces.size());
    }
}
