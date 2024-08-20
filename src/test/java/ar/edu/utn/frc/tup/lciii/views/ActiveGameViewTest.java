package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.services.DashboardService;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ActiveGameViewTest {

    @Mock
    private GameFlowView gameFlowView;
    @Mock
    private GameService gameService;
    @Mock
    private DashboardService dashboardService;

    private ActiveGameView view;

    @Test
    public void testRenderLoadGame() {
        try (MockedStatic<Game> gameMocked = mockStatic(Game.class)) {
            Game game = mock(Game.class);
            when(game.getPlayers()).thenReturn(new LinkedList<>());
            when(game.getIdGame()).thenReturn(null);
            gameMocked.when(Game::getInstance).thenReturn(game);
            view = new ActiveGameView();
            gameService = mock(GameService.class);
            dashboardService = mock(DashboardService.class);
            gameFlowView = mock(GameFlowView.class);
            view.setGameService(gameService);
            view.setDashboardService(dashboardService);
            view.setGameFlowView(gameFlowView);

            when(gameService.createPlayers(any())).thenReturn(new LinkedList<>());
            doNothing().when(dashboardService).createInitialDashboard();
            doNothing().when(dashboardService).setPlayersIntoStartBox(any());

            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(gameFlowView).render();

            view.render();
            verify(gameFlowView, times(1)).render();
        }
    }
}