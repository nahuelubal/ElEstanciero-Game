package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SignUpViewTest {


    private SignupView view;

    @Mock
    private Scanner scanner;

    @Test
    public void testRender2() {
        try (
                MockedStatic<Auth> mockedAuth = mockStatic(Auth.class);
                MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)) {

            scanner = mock(Scanner.class);
            view = new SignupView();

            view.setScanner(scanner);

            ResponseHandler responseHandler = mock(ResponseHandler.class);
            when(responseHandler.getSuccess()).thenReturn(true);

            when(scanner.nextLine()).thenReturn("");

            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(false);
            Auth auth = mock(Auth.class);
            when(auth.register(any())).thenReturn(responseHandler);
            mockedAuth.when(() -> Auth.getInstance(any(), any()))
                    .thenReturn(auth);
            view.render();
            verify(auth, times(1)).register(any());
        }
    }

}
