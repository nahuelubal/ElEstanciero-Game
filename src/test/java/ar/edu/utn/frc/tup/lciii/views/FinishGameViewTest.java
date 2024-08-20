package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.mockito.Mockito.*;

public class FinishGameViewTest {


    @Mock
    private GameService mockGameService;
    @Mock
    private Scanner scanner;
    @Mock
    private PrincipalMenuView mockPrincipalMenuView;

    @BeforeEach
    public void initialize() {
        mockGameService = mock(GameService.class);
        mockPrincipalMenuView = mock(PrincipalMenuView.class);
        scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("\n ");
    }

    @Test
    void testRenderWon() {

        try (MockedStatic<ConsolePrinter> consolePrinter = mockStatic(ConsolePrinter.class)) {

            when(mockGameService.saveOrUpdateGame(any())).thenReturn(new GameEntity());

            FinishGameView view = new FinishGameView(FinishGameView.USER_WON);
            view.setScanner(scanner);
            view.setGameService(mockGameService);
            view.setPrincipalMenuView(mockPrincipalMenuView);
            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(mockPrincipalMenuView).render();

            view.render();

            consolePrinter.verify(() -> ConsolePrinter.println("YOU WON"), times(1));
            consolePrinter.verify(() -> ConsolePrinter.println("Congratulations!"), times(1));
            consolePrinter.verify(() -> ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA), times(1));
            verify(mockGameService, times(1)).saveOrUpdateGame(any());
        }
    }
    @Test
    void testRenderSomeoneHasEnoughMoneyToWin() {

        try (MockedStatic<ConsolePrinter> consolePrinter = mockStatic(ConsolePrinter.class)) {

            when(mockGameService.saveOrUpdateGame(any())).thenReturn(new GameEntity());

            FinishGameView view = new FinishGameView(FinishGameView.SOMEONE_HAS_ENOUGH_MONEY_TO_WIN);
            view.setScanner(scanner);
            view.setGameService(mockGameService);
            view.setPrincipalMenuView(mockPrincipalMenuView);
            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(mockPrincipalMenuView).render();

            view.render();

            consolePrinter.verify(() -> ConsolePrinter.println("Somebody reached the money goal!"), times(1));
            consolePrinter.verify(() -> ConsolePrinter.println("Get luck next time!"), times(1));
            consolePrinter.verify(() -> ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA), times(1));
            verify(mockGameService, times(1)).saveOrUpdateGame(any());
        }
    }  @Test
    void testRenderBankrupted() {

        try (MockedStatic<ConsolePrinter> consolePrinter = mockStatic(ConsolePrinter.class)) {

            when(mockGameService.saveOrUpdateGame(any())).thenReturn(new GameEntity());

            FinishGameView view = new FinishGameView(FinishGameView.USER_BANKRUPTED);
            view.setScanner(scanner);
            view.setGameService(mockGameService);
            view.setPrincipalMenuView(mockPrincipalMenuView);
            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(mockPrincipalMenuView).render();

            view.render();

            consolePrinter.verify(() -> ConsolePrinter.println("Oh no! You got bankrupt! :,("), times(1));
            consolePrinter.verify(() -> ConsolePrinter.println("Get luck next time!"), times(1));
            consolePrinter.verify(() -> ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA), times(1));
            verify(mockGameService, times(1)).saveOrUpdateGame(any());
        }
    }
}
