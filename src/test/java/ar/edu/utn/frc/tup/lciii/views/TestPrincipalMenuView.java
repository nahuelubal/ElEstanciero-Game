package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TestPrincipalMenuView {
    @Mock
    private NewGameView newGameView;

    @Mock
    private LoadedGamesView loadedGamesView;

    @Mock
    private SettingsView settingsView;

    @Mock
    private Scanner scanner;

    private PrincipalMenuView principalMenuView;

    @Test
    void testRender1() {
        newGameView = mock(NewGameView.class);
        scanner = mock(Scanner.class);

        principalMenuView = new PrincipalMenuView();
        principalMenuView.setNewGameView(newGameView);
        principalMenuView.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("1");

        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("4");
            return null;
        }).when(newGameView).render();

        principalMenuView.render();

        verify(newGameView, times(1)).render();
    }

    @Test
    void testRender2() {
        loadedGamesView = mock(LoadedGamesView.class);
        scanner = mock(Scanner.class);

        principalMenuView = new PrincipalMenuView();
        principalMenuView.setLoadedGamesView(loadedGamesView);
        principalMenuView.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("2");

        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("4");
            return null;
        }).when(loadedGamesView).render();

        principalMenuView.render();

        verify(loadedGamesView, times(1)).render();
    }

    @Test
    void testRender3() {
        settingsView = mock(SettingsView.class);
        scanner = mock(Scanner.class);

        principalMenuView = new PrincipalMenuView();
        principalMenuView.setSettingsView(settingsView);
        principalMenuView.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("3");

        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("4");
            return null;
        }).when(settingsView).render();

        principalMenuView.render();

        verify(settingsView, times(1)).render();
    }
}