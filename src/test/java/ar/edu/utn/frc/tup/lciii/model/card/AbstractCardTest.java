package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.box.CardBox;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.ConcretePlayer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractCardTest {
    static class TestCard extends AbstractCard {
        public TestCard(int idCard, String description, Integer value, Boolean isDestiny, Integer boxId) {
            super(idCard, description, value, isDestiny, boxId);
        }

        @Override
        public void actionCard(AbstractPlayer player) {

        }
    }
    @Test
    void testAbstractCardConstructor() {
        String description = "Test description";
        Integer value = 100;
        Boolean isDestiny = true;
        Integer boxId = 10;


        TestCard testCard = new TestCard(1, description, value, isDestiny, boxId);


        assertEquals(1, testCard.getIdCard());
        assertEquals(description, testCard.getDescription());
        assertEquals(value, testCard.getValue());
        assertEquals(isDestiny, testCard.getIsDestiny());
        assertEquals(boxId, testCard.getBoxId());
    }


}