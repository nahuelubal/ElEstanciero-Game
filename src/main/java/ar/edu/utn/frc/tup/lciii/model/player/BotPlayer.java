package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public abstract class BotPlayer extends AbstractPlayer {

    //Constructor
    public BotPlayer(int idPlayer, GameStyle gameStyle, Color color, Integer playerOrder, int moneyAvailable, List<AbstractDeed> deeds, Boolean isActive, int idBox, boolean isPrisoner, int roundsStayQuantity, boolean hasHabeasCorpus) {
        super(idPlayer, gameStyle, color, playerOrder, moneyAvailable, deeds, isActive, idBox, isPrisoner, roundsStayQuantity, hasHabeasCorpus);
    }
}
