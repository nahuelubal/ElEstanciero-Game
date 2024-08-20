package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestAbstractBox {
    AbstractBox abstractBox;

    @BeforeEach
    void initialize(){
        abstractBox = new ConcreteBox(1, 1, new ArrayList<>());
    }

    @Test
    void addPlayerToBox() {
        AbstractPlayer player = new HumanPlayer();

        player.setPlayerOrder(1);
        player.setMoneyAvailable(1000);
        player.setColor(Color.BLUE);
        player.setIsActive(true);

        abstractBox.addPlayerToBox(player);
        assertTrue(abstractBox.getPlayers().contains(player));
    }

    @Test
    void removePlayer() {
        AbstractPlayer player = new HumanPlayer();

        player.setPlayerOrder(1);
        player.setMoneyAvailable(1000);
        player.setColor(Color.BLUE);
        player.setIsActive(true);

        List<AbstractPlayer> players = new ArrayList<>();
        players.add(player);

        abstractBox.setPlayers(players);

        assertEquals(1, abstractBox.getPlayers().size());


        abstractBox.removePlayer(player);

        assertTrue(abstractBox.getPlayers().isEmpty());
    }
}