package ar.edu.utn.frc.tup.lciii.model.deeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProvinceTest {

    private Province province;

    @BeforeEach
    public void setUp(){
        province = new Province();
        province.setRentValue(500);
        province.setRent_1_farm(1200);
        province.setRent_2_farm(1400);
        province.setRent_3_farm(1600);
        province.setRent_4_farm(1800);
        province.setRent_ranch(2000);
    }
    @Test
    void testConstructor() {
        Zone zone = new Zone(1,"CENTER");
        Province  province1 = new Province(1, "Test Province", 1000, 500, false, false,
                1, 2, true, zone, 300, 100,
                150, 200, 250, 300, 400, DeedType.PROVINCE);
        assertNotNull(province1);
        assertEquals(1, province1.getIdDeed());
        assertEquals("Test Province", province1.getName());
        assertEquals(1000, province1.getPurchaseValue());
        assertEquals(500, province1.getMortgageValue());
        assertEquals(1, province1.getIdProvince());
        assertEquals(2, province1.getFarmQuantity());
        assertEquals(true, province1.getHasRanch());
        assertEquals(zone, province1.getZone());
        assertEquals(300, province1.getConstructionValue());
        assertEquals(100, province1.getRentValue());
        assertEquals(150, province1.getRent_1_farm());
        assertEquals(200, province1.getRent_2_farm());
        assertEquals(250, province1.getRent_3_farm());
        assertEquals(300, province1.getRent_4_farm());
        assertEquals(400, province1.getRent_ranch());
        assertEquals(DeedType.PROVINCE, province1.getDeedType());
    }
    @Test
    void testDefaultConstructor() {
        Province defaultConstructor = new Province();
        assertEquals(false, defaultConstructor.getHasRanch());
        assertEquals(0, defaultConstructor.getFarmQuantity());
    }

    @Test
    @DisplayName("getRent() sin chacras, estancias ni provincia completa")
    void getRent() {
        province.setHasRanch(false);
        province.setFarmQuantity(0);

        assertEquals(500, province.getRent(false));
    }

    @Test
    @DisplayName("getRent() con la provincia completa, pero sin chacras ni estancias")
    void getRentWithProvinceComplete() {
        province.setHasRanch(false);
        province.setFarmQuantity(0);

        assertEquals(1000, province.getRent(true));
    }

    @Test
    @DisplayName("getRent() con una chacra")
    void getRentWith1Farm() {
        province.setHasRanch(false);
        province.setFarmQuantity(1);

        assertEquals(1200, province.getRent(true));
    }

    @Test
    @DisplayName("getRent() con dos chacras")
    void getRentWith2Farm() {
        province.setHasRanch(false);
        province.setFarmQuantity(2);

        assertEquals(1400, province.getRent(true));
    }

    @Test
    @DisplayName("getRent() con tres chacras")
    void getRentWith3Farm() {
        province.setHasRanch(false);
        province.setFarmQuantity(3);

        assertEquals(1600, province.getRent(true));
    }

    @Test
    @DisplayName("getRent() con cuatro chacras")
    void getRentWith4Farm() {
        province.setHasRanch(false);
        province.setFarmQuantity(4);

        assertEquals(1800, province.getRent(true));
    }

    @Test
    @DisplayName("getRent() con una estancia")
    void getRentWithRanch() {
        province.setHasRanch(true);
        province.setFarmQuantity(0);

        assertEquals(2000, province.getRent(true));
    }
}