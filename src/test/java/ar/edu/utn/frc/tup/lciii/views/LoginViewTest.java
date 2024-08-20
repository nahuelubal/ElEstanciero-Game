package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.repository.user.UserRepository;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;

import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoginViewTest {

    @Mock
    private SignupView signUpView;

    @Mock
    private PrincipalMenuView principalMenuView;

    private LoginView view;

    @Mock
    private Scanner scanner;

    @Test
    public void testRender() {
        principalMenuView = mock(PrincipalMenuView.class);
        scanner = mock(Scanner.class);
        view = new LoginView();

        view.setPrincipalMenuView(principalMenuView);
        view.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("sa");


        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            return null;
        }).when(principalMenuView).render();


        view.render();

        verify(principalMenuView, times(1)).render();


    }

    @Test
    public void testRender2() {
        try (MockedStatic<Auth> mockedAuth = mockStatic(Auth.class);
                MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {

            signUpView = mock(SignupView.class);
            scanner = mock(Scanner.class);
            view = new LoginView();

            view.setSignupView(signUpView);
            view.setScanner(scanner);

            ResponseHandler responseHandler = mock(ResponseHandler.class);
            when(responseHandler.getSuccess()).thenReturn(false);

            when(scanner.nextLine()).thenReturn("");

            doAnswer((Answer<Void>) invocation -> {
                System.out.println("Performing action!");
                when(responseHandler.getSuccess()).thenReturn(true);

                return null;
            }).when(signUpView).render();

            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(false);
            Auth auth = mock(Auth.class);
            when(auth.login(any())).thenReturn(responseHandler);
            mockedAuth.when(() -> Auth.getInstance(any(),any()))
                    .thenReturn(auth);
            view.render();

            verify(signUpView, times(1)).render();
        }
    }
}
