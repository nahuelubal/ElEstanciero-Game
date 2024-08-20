package ar.edu.utn.frc.tup.lciii.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputValidatorTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    void testIsValidNumberInRange() {
       boolean result =  InputValidator.isValidNumberInRange("2" , 1 , 4);
       assertTrue(result);
    }

    @Test
    void testIsValidNumberInRangeFalse() {
        boolean result =  InputValidator.isValidNumberInRange("5" , 1 , 4);
        assertFalse(result);
    }
    @Test
    void testGetYesNoAnswer() {
        String simulatedInput = "y\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);
        boolean result = InputValidator.getYesNoAnswer();

        System.setIn(System.in);

        assertTrue(result);
    }

    @Test
    void testGetYesNoAnswerNInput() {
        String simulatedInput = "n\n";  // Simulamos una entrada 'n'
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inputStream);

        boolean result = InputValidator.getYesNoAnswer();

        System.setIn(System.in);

        assertFalse(result);
    }
    @Test
    void testIsValidNumber() {
       boolean response = InputValidator.isValidNumber("5");
        assertTrue(response);
    }

    @Test
    void testIsValidNumberError(){
        boolean response = InputValidator.isValidNumber("*");
        assertFalse(response);
    }
    @Test
    void testBuildRegexForRange() throws NoSuchMethodException,IllegalAccessException, InvocationTargetException {

        Method method = InputValidator.class.getDeclaredMethod("buildRegexForRange", int.class, int.class);
        method.setAccessible(true);

        String regex = (String) method.invoke(null, 1, 4);

        // Verifica que el resultado sea el esperado usando assertEquals
        assertEquals("^[1-4]$", regex);
    }

    @Test
    void testGetYesNoAnswerInvalidInput() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String simulatedInput = "invalid\nn\n";
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Method method = InputValidator.class.getDeclaredMethod("getYesNoAnswer");
        method.setAccessible(true);
        Boolean result = (Boolean) method.invoke(null);

        assertFalse(result);

        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid answer"));
    }
}