package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChangePasswordViewTest {
    @Mock
    private Scanner scanner;

    @Test
    void renderChangePassword() {
        try (MockedStatic<Auth> mockedAuth = mockStatic(Auth.class);
             MockedStatic<ConsolePrinter> mockedConsole = mockStatic(ConsolePrinter.class)) {
            scanner = mock(Scanner.class);

            ChangePasswordView changePasswordView = new ChangePasswordView();
            changePasswordView.setScanner(scanner);

            when(scanner.nextLine()).thenReturn("password");

            ResponseHandler responseHandler = mock(ResponseHandler.class);
            when(responseHandler.getSuccess()).thenReturn(true);

            Auth auth = mock(Auth.class);
            when(auth.changePassword(any(), any())).thenReturn(responseHandler);

            mockedAuth.when(() -> Auth.getInstance(any(), any()))
                    .thenReturn(auth);

            changePasswordView.render();
            mockedConsole.verify(() -> ConsolePrinter.printlnColor("✓ Successfully password changed!", ConsoleColor.BRIGHT_GREEN), times(1));
            verify(auth, times(1)).changePassword(any(), any());
        }
    }

    @Test
    void renderCantChangePassword() {
        try (MockedStatic<Auth> mockedAuth = mockStatic(Auth.class);
             MockedStatic<ConsolePrinter> mockedConsole = mockStatic(ConsolePrinter.class);
             MockedStatic<InputValidator> mockedValidator = mockStatic(InputValidator.class)
        ) {
            scanner = mock(Scanner.class);
            mockedValidator.when(InputValidator::getYesNoAnswer).thenReturn(false);

            ChangePasswordView changePasswordView = new ChangePasswordView();
            changePasswordView.setScanner(scanner);

            when(scanner.nextLine()).thenReturn("password");

            ResponseHandler responseHandler = mock(ResponseHandler.class);
            when(responseHandler.getSuccess()).thenReturn(false);

            Auth auth = mock(Auth.class);
            when(auth.changePassword(any(), any())).thenReturn(responseHandler);

            mockedAuth.when(() -> Auth.getInstance(any(), any()))
                    .thenReturn(auth);

            changePasswordView.render();
            mockedConsole.verify(() -> ConsolePrinter.printlnColor("Χ Password change failed! " + responseHandler.getMessage(), ConsoleColor.BRIGHT_RED),times(1));
            verify(auth, times(1)).changePassword(any(), any());
        }
    }
}