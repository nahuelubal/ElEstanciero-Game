package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;

import java.util.ArrayList;

class AbstractPlayerTest {
    AbstractPlayer abstractPlayer = new ConcretePlayer(1, null, Color.BLACK, 1, 1, new ArrayList<>(), true, 1,false, 0, false);
}