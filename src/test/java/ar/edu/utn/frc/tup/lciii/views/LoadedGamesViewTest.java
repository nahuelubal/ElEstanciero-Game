package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class LoadedGamesViewTest {
    private LoadedGamesView view;

    @Mock
    private Scanner scanner;
    @Mock
    private GameService gameService;
    private User user;

    @Test
    public void testRenderNoGames() {
        try (MockedStatic<Session> mockedStatic = mockStatic(Session.class);
             MockedStatic<ConsolePrinter> mockedConsole = mockStatic(ConsolePrinter.class);

        ) {

            scanner = mock(Scanner.class);
            view = new LoadedGamesView();
            user = new User();
            user.setIdUser(1);
            mockedStatic.when(Session::getUser).thenReturn(user);

            gameService = mock(GameService.class);

            view.setScanner(scanner);
            view.setGameService(gameService);

            when(gameService.getGamesByUserId(anyInt())).thenReturn(new ArrayList<>());


            when(scanner.nextLine()).thenReturn("");

            view.render();
            mockedConsole.verify(() -> ConsolePrinter.println("You don't have any saved game yet."), times(1));
        }
    }
    @Test
    public void testRenderSelectGame() {
        try (MockedStatic<Session> mockedStatic = mockStatic(Session.class)) {

            scanner = mock(Scanner.class);
            ManageLoadedGameView manageLoadedGameView = mock(ManageLoadedGameView.class);
            view = new LoadedGamesView();
            user = new User();
            user.setIdUser(1);
            mockedStatic.when(Session::getUser).thenReturn(user);

            gameService = mock(GameService.class);

            view.setScanner(scanner);
            List<SavedGame> games = new ArrayList<>();
            games.add(new SavedGame(1, LocalDateTime.now()));
            when(gameService.getGamesByUserId(anyInt())).thenReturn(games);
            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(manageLoadedGameView).render();
            view.setManageLoadedGameView(manageLoadedGameView);
            view.setGameService(gameService);

            when(scanner.nextLine()).thenReturn("1");

            view.render();
            verify(manageLoadedGameView, times(1)).render();
        }
    }
}
