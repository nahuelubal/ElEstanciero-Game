package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class ConcreteBotPlayer extends BotPlayer {
    public ConcreteBotPlayer(int idPlayer, GameStyle gameStyle, Color color, int playerOrder, int moneyAvailable, ArrayList<AbstractDeed> abstractDeeds, boolean isActive,int idBox, boolean isPrisoner, int roundsStayQuantity, boolean hasHabeasCorpus) {
        super(idPlayer, gameStyle, color, playerOrder, moneyAvailable, abstractDeeds, isActive, idBox, isPrisoner, roundsStayQuantity, false);
    }

    @Override
    public void buyProperty(AbstractDeed property) {

    }

    @Override
    public void addRanch(Province province) {

    }

    @Override
    public void addFarm(Province province, Integer quantity) {

    }

    @Override
    public void pay(int money) {

    }

    @Override
    public void receive(int money) {

    }

    @Override
    public void choosePropertyToMortgage() {

    }

    @Override
    public void mortgageProperty(AbstractDeed property) {

    }
}
