package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class WelcomeViewTest {
    @Mock
    private Scanner scanner;

    @Mock
    private LoginOrSignupView loginOrSignupView;

    private WelcomeView welcomeView;

    @Test
    void render() {
        scanner = mock(Scanner.class);
        loginOrSignupView = mock(LoginOrSignupView.class);

        welcomeView = new WelcomeView();
        welcomeView.setScanner(scanner);
        welcomeView.setLoginOrSignupView(loginOrSignupView);

        when(scanner.nextLine()).thenReturn("\n");

        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            return null;
        }).when(loginOrSignupView).render();

        welcomeView.render();

        verify(loginOrSignupView, times(1)).render();
    }
}