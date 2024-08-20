package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StyleGameTest {
    List<Province> provinces = new ArrayList<>();
    GameStyle gameStyle = new GameStyle(1, BotPlayerTypes.CONSERVATIVE, true, true, 1, new ArrayList<>());

    @Test
    void isRailwayPurchase() {
    }

    @Test
    void setRailwayPurchase() {
    }

    @Test
    void isCompanyPurchase() {
    }

    @Test
    void setCompanyPurchase() {
    }

    @Test
    void getProvincesToPrioritize() {
    }

    @Test
    void setProvincesToPrioritize() {
    }
}