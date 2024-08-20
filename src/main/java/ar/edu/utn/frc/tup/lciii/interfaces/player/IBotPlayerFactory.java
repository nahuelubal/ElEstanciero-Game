package ar.edu.utn.frc.tup.lciii.interfaces.player;

import ar.edu.utn.frc.tup.lciii.model.player.BotPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;

public interface IBotPlayerFactory {
    BotPlayer createBotPlayer(BotPlayerTypes botPlayerTypes);
}
