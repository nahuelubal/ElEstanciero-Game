package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class BankTest {
    Bank bank = Bank.getInstance();
    @Mock
    HumanPlayer player;

    @BeforeEach
    void initializae(){
        bank.setMoneyAvailable(1000);
        player = mock(HumanPlayer.class);
    }

    @Test
    void pay() {
        doNothing().when(player).pay(10);
        bank.pay(10);
        player.receive(10);
        assertEquals(990, bank.getMoneyAvailable());
    }

    @Test
    void receive() {
        doNothing().when(player).receive(10);
        player.pay(10);
        bank.receive(10);
        assertEquals(1010, bank.getMoneyAvailable());
    }
}