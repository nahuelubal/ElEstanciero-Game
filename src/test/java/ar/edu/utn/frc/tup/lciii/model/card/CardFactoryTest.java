package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.services.impl.CardFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

	private final CardFactoryImpl cardFactory = new ConcreteCardFactory();

	@Test
	void testCreateCardCharge() {
		AbstractCard card = cardFactory.createCard(CardType.CHARGE);
		assertInstanceOf(ChargeCard.class, card);
	}

	@Test
	void testCreateCardMoveByBox() {

		AbstractCard card = cardFactory.createCard(CardType.MOVE_BY_BOX);
		assertInstanceOf(MovementBoxCard.class, card);
	}

	@Test
	void testCreateCardPay() {
		AbstractCard card = cardFactory.createCard(CardType.PAY);
		assertInstanceOf(PaymentCard.class, card);
	}
	@Test
	void testGetCardTypePayment() {
		assertEquals(PaymentCard.class, cardFactory.getTypeOfCard(CardType.PAY));
	}
	@Test
	void testGetCardTypeCharge() {
		assertEquals(ChargeCard.class, cardFactory.getTypeOfCard(CardType.CHARGE));
	}
}