package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    List<AbstractBox> boxes = new ArrayList<>();
    Queue<AbstractPlayer> players = new LinkedList<>();
    Game game = Game.getInstance();

    @Test
    void startGame() {
    }

    @Test
    void incrementRound() {
    }

    @Test
    void saveGame() {
    }

    @Test
    void endGame() {
    }
}