package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.player.ConservativeBotPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCardTest {

    PaymentCard payment;
    HumanPlayer player;


    @BeforeEach
    public void setUp() {
        payment = new PaymentCard();
        player = new HumanPlayer();
    }

    @Test
    void testActionCard() {
        player.setMoneyAvailable(5000);
        payment.setValue(1000);
        payment.actionCard(player);
        assertEquals(4000, player.getMoneyAvailable());
    }

    @Test
    void test2ActionCard() {
        player.setMoneyAvailable(1000);
        payment.setValue(1000);
        payment.actionCard(player);
        assertEquals(0, player.getMoneyAvailable());
    }

}