package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.WellBalancedBotPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChargeCardTest {
	HumanPlayer player;
	ChargeCard card;

	@BeforeEach
	public void setUp() {
		card = new ChargeCard();
		card.setValue(10);
		player = new HumanPlayer();
	}
	@Test
	void testActionCard() {
		player.setMoneyAvailable(5000);
		card.setValue(1000);
		card.actionCard(player);
		assertEquals(6000, player.getMoneyAvailable());
	}
}