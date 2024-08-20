package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;

import java.util.List;
import java.util.Queue;

public interface GameService {
    List<SavedGame> getGamesByUserId(int userId);

    Game getGameById(int id);

    GameEntity saveOrUpdateGame(Game game);

    void deleteGameById(int id);

    Queue<AbstractPlayer> createPlayers(Difficulty difficulty);
    Queue<AbstractPlayer> getAllPlayersByGameId(int gameId);

    Color getRandomColor(List<Integer> indexColors);
}
