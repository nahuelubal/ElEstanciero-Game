package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;

import java.util.Queue;

public interface PlayerService {
    Queue<AbstractPlayer> getAllPlayersByGameId(int gameId);
    void saveOrUpdatePlayer(AbstractPlayer player);
    GameStyleEntity getGameStyleByName(GameStyle gameStyle);
    GameStyle getGameStyleByBotType(BotPlayerTypes botPlayerType);
    BoxEntity getBoxById(int boxId);
}
