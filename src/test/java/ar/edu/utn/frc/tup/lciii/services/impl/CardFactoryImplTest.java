package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.card.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryImplTest {

    @Test
    void testCreateCard() {
        // Test case for each CardType
        AbstractCard card1 = CardFactoryImpl.createCard(CardType.MOVE_BY_BOX);
        assertTrue(card1 instanceof MovementBoxCard);

        AbstractCard card2 = CardFactoryImpl.createCard(CardType.MOVE_BY_QUANTITY);
        assertTrue(card2 instanceof MovementQuantityCard);

        AbstractCard card3 = CardFactoryImpl.createCard(CardType.CHARGE);
        assertTrue(card3 instanceof ChargeCard);

        AbstractCard card4 = CardFactoryImpl.createCard(CardType.PAY_UPGRADE);
        assertTrue(card4 instanceof PaymentUpgradeCard);

        AbstractCard card5 = CardFactoryImpl.createCard(CardType.PAY);
        assertTrue(card5 instanceof PaymentCard);
    }

}