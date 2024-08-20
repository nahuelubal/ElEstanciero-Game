package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dice;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

class RestBoxTest {
    private RestBox restBox;
    private AbstractPlayer player;
    private Game game;
    private Dice dice;

    @BeforeEach
    void setUp() {
        restBox = new RestBox();
        player = mock(HumanPlayer.class);
        game = mock(Game.class);
        dice = mock(Dice.class);
    }

    @Test
    @DisplayName("actionBox() el player decide descansar y no saca doble en los dados")
    void actionBoxPlayerRests() {
        try (MockedStatic<Game> mockedGame = mockStatic(Game.class);
             MockedStatic<ConsolePrinter> mockedPrinter = mockStatic(ConsolePrinter.class);
             MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {

            mockedGame.when(Game::getInstance).thenReturn(game);
            when(game.getDice()).thenReturn(dice);
            when(dice.areDicesEquals()).thenReturn(false);
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(true);

            when(player.getRoundsStayQuantity()).thenReturn(0);

            restBox.actionBox(player);

            mockedPrinter.verify(() -> ConsolePrinter.println("¿Do you want to rest?"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Before, roll dices"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Select Y (Yes) / N (No):"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("The player is in the rest box"), times(0));
            verify(player).setIsActive(false);
            verify(player).addRoundsStayQuantity();
        }
    }

    @Test
    @DisplayName("actionBox() el player decide no descansar")
    void actionBoxPlayerDoesNotRest() {
        try (MockedStatic<Game> mockedGame = mockStatic(Game.class);
             MockedStatic<ConsolePrinter> mockedPrinter = mockStatic(ConsolePrinter.class);
             MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {

            mockedGame.when(Game::getInstance).thenReturn(game);
            when(game.getDice()).thenReturn(dice);
            when(dice.areDicesEquals()).thenReturn(false);
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(false);

            when(player.getRoundsStayQuantity()).thenReturn(0);

            restBox.actionBox(player);

            mockedPrinter.verify(() -> ConsolePrinter.println("¿Do you want to rest?"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Before, roll dices"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Select Y (Yes) / N (No):"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("The player is in the rest box"), times(1));
            verify(player).setIsActive(true);
            verify(player, never()).addRoundsStayQuantity();
        }
    }

    @Test
    @DisplayName("actionBox() el player saca dobles")
    void actionBoxPlayerRollsDoubles() {
        try (MockedStatic<Game> mockedGame = mockStatic(Game.class);
             MockedStatic<ConsolePrinter> mockedPrinter = mockStatic(ConsolePrinter.class);
             MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {

            mockedGame.when(Game::getInstance).thenReturn(game);
            when(game.getDice()).thenReturn(dice);
            when(dice.areDicesEquals()).thenReturn(true);
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(true);

            when(player.getRoundsStayQuantity()).thenReturn(0);

            restBox.actionBox(player);

            mockedPrinter.verify(() -> ConsolePrinter.println("¿Do you want to rest?"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Before, roll dices"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("Select Y (Yes) / N (No):"), times(1));
            mockedPrinter.verify(() -> ConsolePrinter.println("The player is in the rest box"), times(1));
            verify(player).setIsActive(true);
            verify(player, never()).addRoundsStayQuantity();
        }
    }

    @Test
    @DisplayName("actionBox() el player ya ha descansado dos turnos")
    void actionBoxPlayerAlreadyRestedTwoTurns() {
        try (MockedStatic<ConsolePrinter> mockedPrinter = mockStatic(ConsolePrinter.class)) {

            when(player.getRoundsStayQuantity()).thenReturn(2);

            restBox.actionBox(player);

            mockedPrinter.verify(() -> ConsolePrinter.println("¿Do you want to rest?"), times(0));
            mockedPrinter.verify(() -> ConsolePrinter.println("Before, roll dices"), times(0));
            mockedPrinter.verify(() -> ConsolePrinter.println("Select Y (Yes) / N (No):"), times(0));
            mockedPrinter.verify(() -> ConsolePrinter.println("The player is in the rest box"), times(1));
            verify(player).setIsActive(true);
            verify(player).setRoundsStayQuantity(0);
        }
    }
}
