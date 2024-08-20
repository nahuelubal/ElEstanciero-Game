package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DefineMoneyToWinViewTest {

    private DefineMoneyToWinView view;

    @Mock
    private Scanner scanner;
    @Mock
    private ActiveGameView activeGameView;
    @Test
    public void testRender1 () {
        try (MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {
            view = new DefineMoneyToWinView();
            activeGameView = mock(ActiveGameView.class);
            view.setActiveGameView(activeGameView);

            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(activeGameView).render();
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(false);

            view.render();
            verify(activeGameView, times(1)).render();
        }
    }
    @Test
    public void testRender2() {
        try (MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class);) {

            scanner = mock(Scanner.class);
            view = new DefineMoneyToWinView();
            activeGameView = mock(ActiveGameView.class);
            view.setActiveGameView(activeGameView);
            when(scanner.nextLine()).thenReturn("1000000");

            view.setScanner(scanner);
            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                return null;
            }).when(activeGameView).render();
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(true);
            mockedValidator.when(() -> InputValidator.isValidNumber(any()))
                    .thenReturn(true);
            view.render();
            verify(activeGameView, times(1)).render();
            assertEquals(Game.getInstance().getIsWinByMoneyObjective(), true);
        }
    }
}
