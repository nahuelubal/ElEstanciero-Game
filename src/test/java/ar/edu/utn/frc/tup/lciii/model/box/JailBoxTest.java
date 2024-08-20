package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.config.Dice;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.card.DeckOfCard;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class JailBoxTest {

    private JailBox jailBox;
    private AbstractPlayer mockPlayer;
    private Game mockGame;
    private Dice mockDice;
    private Dashboard mockDashboard;
    private DeckOfCard mockLuckyCards;
    private DeckOfCard mockDestinyCards;
    @Mock
    private BotPlayer botPlayer;

    private final InputStream systemIn = System.in;

    @BeforeEach
    void setUp() {
        mockPlayer = Mockito.mock(HumanPlayer.class);
        mockGame = Mockito.mock(Game.class);
        mockDice = Mockito.mock(Dice.class);
        mockDashboard = Mockito.mock(Dashboard.class);
        mockLuckyCards = Mockito.mock(DeckOfCard.class);
        mockDestinyCards = Mockito.mock(DeckOfCard.class);
        botPlayer = mock(BotPlayer.class);

        when(mockGame.getDice()).thenReturn(mockDice);
        when(mockDashboard.getLuckyCards()).thenReturn(mockLuckyCards);
        when(mockDashboard.getDestinyCards()).thenReturn(mockDestinyCards);

        Game.setInstance(mockGame);
        Dashboard.setInstance(mockDashboard);

        jailBox = new JailBox(0, 0, new ArrayList<>(), BoxType.JAIL_BOX);
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(systemIn);
    }

    @Test
    void testPlayerPaysToGetOut() {
        when(mockPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(mockPlayer.isHasHabeasCorpus()).thenReturn(false);
        doNothing().when(mockPlayer).pay(1000);
        doNothing().when(mockPlayer).setPrisoner(false);
        doNothing().when(mockPlayer).setRoundsStayQuantity(0);

        simulateConsoleInput("1");

        jailBox.actionBox(mockPlayer);

        verify(mockPlayer).pay(1000);
        verify(mockPlayer).setPrisoner(false);
        verify(mockPlayer).setRoundsStayQuantity(0);
    }

    @Test
    void testPlayerTriesToRollDoubles() {
        when(mockPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(mockPlayer.isHasHabeasCorpus()).thenReturn(false);
        when(mockDice.areDicesEquals()).thenReturn(true);

        doNothing().when(mockPlayer).setPrisoner(false);
        doNothing().when(mockPlayer).setRoundsStayQuantity(0);

        simulateConsoleInput("2");

        jailBox.actionBox(mockPlayer);

        verify(mockPlayer).setPrisoner(false);
        verify(mockPlayer).setRoundsStayQuantity(0);
    }

    @Test
    void testPlayerFailsToRollDoubles() {
        when(mockPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(mockPlayer.isHasHabeasCorpus()).thenReturn(false);
        when(mockDice.areDicesEquals()).thenReturn(false);

        simulateConsoleInput("2");

        jailBox.actionBox(mockPlayer);

        verify(mockPlayer, never()).setPrisoner(false);
        verify(mockPlayer, never()).setRoundsStayQuantity(0);
        verify(mockPlayer).addRoundsStayQuantity();
    }

    @Test
    void testPlayerUsesHabeasCorpus() {
        when(mockPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(mockPlayer.isHasHabeasCorpus()).thenReturn(true);

        doNothing().when(mockPlayer).setPrisoner(false);
        doNothing().when(mockPlayer).setRoundsStayQuantity(0);
        doNothing().when(mockPlayer).setHasHabeasCorpus(false);
        when(mockDestinyCards.getTransitionalCards()).thenReturn(new ArrayList<>());
        when(mockLuckyCards.getTransitionalCards()).thenReturn(new ArrayList<>());

        simulateConsoleInput("3");

        jailBox.actionBox(mockPlayer);

        verify(mockPlayer).setPrisoner(false);
        verify(mockPlayer).setRoundsStayQuantity(0);
        verify(mockPlayer).setHasHabeasCorpus(false);
        verify(mockLuckyCards).putHabeasCorpus();
    }

    @Test
    void testBotPlayerUsesHabeasCorpus() {
        when(botPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(botPlayer.isHasHabeasCorpus()).thenReturn(true);

        doNothing().when(botPlayer).setPrisoner(false);
        doNothing().when(botPlayer).setRoundsStayQuantity(0);
        doNothing().when(botPlayer).setHasHabeasCorpus(false);
        when(mockDestinyCards.getTransitionalCards()).thenReturn(new ArrayList<>());
        when(mockLuckyCards.getTransitionalCards()).thenReturn(new ArrayList<>());

        simulateConsoleInput("3");

        jailBox.actionBox(botPlayer);

        verify(botPlayer).setPrisoner(false);
        verify(botPlayer).setRoundsStayQuantity(0);
        verify(botPlayer).setHasHabeasCorpus(false);
        verify(mockLuckyCards).putHabeasCorpus();
    }
    @Test
    void testBotPlayerPaysToGetOut() {
        when(botPlayer.getRoundsStayQuantity()).thenReturn(0);
        when(botPlayer.isHasHabeasCorpus()).thenReturn(false);
        doNothing().when(botPlayer).pay(1000);
        doNothing().when(botPlayer).setPrisoner(false);
        doNothing().when(botPlayer).setRoundsStayQuantity(0);

        simulateConsoleInput("1");

        jailBox.actionBox(botPlayer);

        verify(botPlayer).pay(1000);
        verify(botPlayer).setPrisoner(false);
        verify(botPlayer).setRoundsStayQuantity(0);
    }


    @Test
    void testPlayerIsReleasedAfterTwoTurns() {
        when(mockPlayer.getRoundsStayQuantity()).thenReturn(2);

        jailBox.actionBox(mockPlayer);

        verify(mockPlayer).setPrisoner(false);
    }


    private void simulateConsoleInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}