package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.box.property_box.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.services.impl.BoxFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxFactoryTest {

    private final BoxFactoryImpl boxFactory = new ConcreteBoxFactory();

    @Test
    void testCreateCardBox() {
        AbstractBox box = boxFactory.createBox(BoxType.CARD);
        assertInstanceOf(CardBox.class, box);
    }

    @Test
    void testCreateCompanyPropertyBox() {
        AbstractBox box = boxFactory.createBox(BoxType.COMPANY_PROPERTY);
        assertInstanceOf(CompanyProperty.class , box);
    }

    @Test
    void testCreateProvincePropertyBox() {
        AbstractBox box = boxFactory.createBox(BoxType.PROVINCE_PROPERTY);
        assertInstanceOf(ProvinceProperty.class , box);
    }

    @Test
    void testCreateRailwayPropertyBox() {
        AbstractBox box = boxFactory.createBox(BoxType.RAILWAY_PROPERTY);
        assertInstanceOf(RailwayProperty.class , box);
    }

    @Test
    void testCreateFarmerPrizeBox() {
        AbstractBox box = boxFactory.createBox(BoxType.FARMER_PRIZE);
        assertTrue(box instanceof FarmerPrizeBox);
    }

    @Test
    void testCreateGoToJailBox() {
        AbstractBox box = boxFactory.createBox(BoxType.GO_TO_JAIL);
        assertTrue(box instanceof GoToJailBox);
    }

    @Test
    void testCreateJailBox() {
        AbstractBox box = boxFactory.createBox(BoxType.JAIL_BOX);
        assertTrue(box instanceof JailBox);
    }

    @Test
    void testCreateRestBox() {
        AbstractBox box = boxFactory.createBox(BoxType.REST_BOX);
        assertTrue(box instanceof RestBox);
    }

    @Test
    void testCreateStartBox() {
        AbstractBox box = boxFactory.createBox(BoxType.START_BOX);
        assertTrue(box instanceof StartBox);
    }

    @Test
    void testCreateTaxBox() {
        AbstractBox box = boxFactory.createBox(BoxType.TAX_BOX);
        assertTrue(box instanceof TaxBox);
    }
}