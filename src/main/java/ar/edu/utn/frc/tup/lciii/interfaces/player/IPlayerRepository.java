package ar.edu.utn.frc.tup.lciii.interfaces.player;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;

import java.util.List;

public interface IPlayerRepository {
	List<PlayerEntity> getAll();
	List<PlayerEntity> getPlayersByGameId(int gameId);
	void saveOrUpdatePlayer(PlayerEntity player);
	GameStyleEntity getGameStyleByName(GameStyle gameStyle);
	GameStyleEntity getGameStyleByBotType(BotPlayerTypes botPlayerType);
	BoxEntity getBoxById(int boxId);
}
