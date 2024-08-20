package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class LoginOrSignupViewTest {
    @Mock
    private LoginView loginView;
    @Mock
    private SignupView signUpView;

    private LoginOrSignupView view;

    @Mock
    private Scanner scanner;

    @Test
    public void testRender1() {
        loginView = mock(LoginView.class);
        scanner = mock(Scanner.class);
        view = new LoginOrSignupView();

        view.setLoginView(loginView);
        view.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("1");


        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("3");
            return null;
        }).when(loginView).render();


        view.render();

        verify(loginView, times(1)).render();
    }

    @Test
    public void testRender2() {
        signUpView = mock(SignupView.class);
        scanner = mock(Scanner.class);
        view = new LoginOrSignupView();

        view.setSignupView(signUpView);
        view.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("2");


        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("3");
            return null;
        }).when(signUpView).render();


        view.render();

        verify(signUpView, times(1)).render();
    }
}