package ar.edu.utn.frc.tup.lciii.model.card;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

class CardTypeTest {

	@Test
	void enumValues(){
		CardType[] expectedValues = {CardType.BIRTHDAY, CardType.MOVE_BY_QUANTITY,CardType.MOVE_BY_BOX,CardType.CHARGE,
		CardType.PAY_UPGRADE,CardType.PAY,CardType.EXIT_FROM_JAIL};
		assertArrayEquals(expectedValues , CardType.values());
	}

	@Test
	void valueOf() {
		assertEquals(CardType.PAY , CardType.valueOf("PAY"));
		assertEquals(CardType.CHARGE, CardType.valueOf("CHARGE"));
		assertEquals(CardType.BIRTHDAY, CardType.valueOf("BIRTHDAY"));
		assertEquals(CardType.MOVE_BY_QUANTITY, CardType.valueOf("MOVE_BY_QUANTITY"));
		assertEquals(CardType.MOVE_BY_BOX, CardType.valueOf("MOVE_BY_BOX"));
		assertEquals(CardType.PAY_UPGRADE, CardType.valueOf("PAY_UPGRADE"));
		assertEquals(CardType.EXIT_FROM_JAIL, CardType.valueOf("EXIT_FROM_JAIL"));

	}
}