package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesViewTest {

    @Test
    void render() {
        GameRulesView gameRulesView = new GameRulesView();
        assertDoesNotThrow(gameRulesView::render);
    }
}