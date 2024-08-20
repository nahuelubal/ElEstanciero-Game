package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.card.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTypeTest {

    @Test
    void enumValues(){
        BoxType[] expectedValues = {BoxType.CARD,BoxType.COMPANY_PROPERTY, BoxType.PROVINCE_PROPERTY , BoxType.RAILWAY_PROPERTY
                 , BoxType.FARMER_PRIZE , BoxType.GO_TO_JAIL , BoxType.JAIL_BOX,
                BoxType.REST_BOX ,BoxType.START_BOX, BoxType.TAX_BOX,BoxType.FREE_PARKING};

        assertArrayEquals(BoxType.values(),expectedValues );
    }

    @Test
    void valueOf() {
        assertEquals(BoxType.JAIL_BOX , BoxType.valueOf("JAIL_BOX"));
        assertEquals(BoxType.CARD, BoxType.valueOf("CARD"));
        assertEquals(BoxType.PROVINCE_PROPERTY , BoxType.valueOf("PROVINCE_PROPERTY"));
        assertEquals(BoxType.TAX_BOX , BoxType.valueOf("TAX_BOX"));
        assertEquals(BoxType.START_BOX , BoxType.valueOf("START_BOX"));
        assertEquals(BoxType.FARMER_PRIZE , BoxType.valueOf("FARMER_PRIZE"));
        assertEquals(BoxType.COMPANY_PROPERTY , BoxType.valueOf("COMPANY_PROPERTY"));
        assertEquals(BoxType.RAILWAY_PROPERTY , BoxType.valueOf("RAILWAY_PROPERTY"));
        assertEquals(BoxType.REST_BOX , BoxType.valueOf("REST_BOX"));
        assertEquals(BoxType.GO_TO_JAIL , BoxType.valueOf("GO_TO_JAIL"));
        assertEquals(BoxType.FREE_PARKING , BoxType.valueOf("FREE_PARKING"));
    }
}