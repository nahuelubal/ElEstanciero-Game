package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.services.DashboardService;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import org.glassfish.jaxb.runtime.v2.runtime.BinderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ManageLoadedGameViewTest {

    private ManageLoadedGameView view;

    @Mock
    private Scanner scanner;
    @Mock
    private GameService gameService;

    private GameFlowView gameFlowView;
    @Mock
    private DashboardService dashboardService;

    private Game game;

    private SavedGame savedGame;

    @BeforeEach
    public void initialize(){
        savedGame = new SavedGame();
        savedGame.setIdGame(1);

        game = Game.getInstance();
        game.setPlayers(new LinkedList<>());

        gameService = mock(GameService.class);
        dashboardService = mock(DashboardService.class);
        gameFlowView = mock(GameFlowView.class);
    }

    @Test
    public void testRenderLoadGame() {
        try (MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class);
             MockedStatic<ConsolePrinter> mockedConsole = mockStatic(ConsolePrinter.class)) {

            view = new ManageLoadedGameView(savedGame);

            view.setGameService(gameService);
            view.setDashboardService(dashboardService);
            view.setGameFlowView(gameFlowView);
            mockedValidator.when(() -> InputValidator.isValidNumberInRange(any(),anyInt(),anyInt()))
                    .thenReturn(true);
            when(gameService.getGameById(anyInt())).thenReturn(game);
            doNothing().when(dashboardService).createInitialDashboard();
            doNothing().when(dashboardService).setPlayersIntoTheirBoxes(any());


            scanner = mock(Scanner.class);
            when(scanner.nextLine()).thenReturn("1");

            view.setScanner(scanner);

            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(gameFlowView).render();

            view.render();
            verify(gameFlowView,times(1)).render();
            mockedConsole.verify(() -> ConsolePrinter.println("Loading game..."), times(1));
        }
    }
    @Test
    public void testRenderDeleteGame() {
        try (MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class);
             MockedStatic<ConsolePrinter> mockedConsole = mockStatic(ConsolePrinter.class)) {

            view = new ManageLoadedGameView(savedGame);

            view.setGameService(gameService);
            view.setDashboardService(dashboardService);
            view.setGameFlowView(gameFlowView);
            mockedValidator.when(() -> InputValidator.isValidNumberInRange(any(),anyInt(),anyInt()))
                    .thenReturn(true);
            when(gameService.getGameById(anyInt())).thenReturn(game);
            doNothing().when(gameService).deleteGameById(1);


            scanner = mock(Scanner.class);
            when(scanner.nextLine()).thenReturn("2");

            view.setScanner(scanner);

            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(gameFlowView).render();

            view.render();
            verify(gameService,times(1)).deleteGameById(1);
            mockedConsole.verify(() -> ConsolePrinter.println("Deleting game..."), times(1));
        }
    }

}
