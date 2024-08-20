package ar.edu.utn.frc.tup.lciii.interfaces.game;

import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedByPlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.config.Game;

import java.util.List;

public interface IGameRepository {
    List<GameEntity> getGamesByUserId(int userId);

    GameEntity saveOrUpdateGame(GameEntity game, List<PlayerEntity> playerEntityList, List<DeedByPlayerEntity> deedByPlayerEntities);

    void deleteGameById(int id);

    GameEntity getGameById(int id);

    DifficultyEntity getGameDifficultyByName(Difficulty difficulty);

    ColorEntity getColorByName(Color color);
}
