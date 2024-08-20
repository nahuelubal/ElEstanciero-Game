package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * This class creates the basic game configuration
 * like players, colors, etc
 */
@Data
@AllArgsConstructor
public class Game {
    private static final String YES_NO_REGEX = "^[yYnN]$";
    //Attributes
    private Integer idGame;
    private Dashboard dashboard;
    private Integer roundNumber;
    private Integer moneyObjectiveToWin;
    private Queue<AbstractPlayer> players;
    private User user;
    private Difficulty difficulty;
    private Bank bank;
    private Boolean isWinByMoneyObjective;
    private Dice dice = new Dice();
    private Boolean isFinish;


    // Constructor
    private Game() {
        this.roundNumber = 0;
        this.isWinByMoneyObjective = false;
        this.isFinish = false;
        this.bank = Bank.getInstance();
    }

    // Singleton
    private static class Holder {
        private static Game INSTANCE = new Game();
    }

    public static Game getInstance() {
        return Game.Holder.INSTANCE;
    }

    public static void setInstance(Game game) {
        Holder.INSTANCE = game;
    }

    public void cleanInstance() {
        idGame = null;
        difficulty = null;
        roundNumber = 0;
        moneyObjectiveToWin = 0;
        isWinByMoneyObjective = false;
        players = new LinkedList<>();
    }


    //Methods
    public Optional<AbstractPlayer> somePlayerHasProperty(AbstractDeed deed) {
        for (AbstractPlayer currentPlayer : players) {
            if (currentPlayer.hasProperty(deed)) {
                return Optional.of(currentPlayer);
            }
        }
        return Optional.empty();
    }

    public HumanPlayer getUserPlayer(){
        for(AbstractPlayer player : players){
            if (player instanceof HumanPlayer){
                return (HumanPlayer) player;
            }
        }
        return null;
    }

    public AbstractPlayer getPlayerByColor(Color color){
        for(AbstractPlayer player : players){
            if (player.getColor().equals(color)){
                return player;
            }
        }

        return null;
    }

    public void removePlayer(AbstractPlayer player){
        players.remove(player);
    }


    public void incrementRound() {

    }

    public void saveGame() {

    }

    public void endGame() {

    }
}
