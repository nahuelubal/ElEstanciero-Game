package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChooseDifficultyViewTest {

    @Mock
    private ChooseColorView chooseColorView;
    private ChooseDifficultyView view;
    @Mock
    private Scanner scanner;

    @Test
    void render() {
        chooseColorView = mock(ChooseColorView.class);
        scanner = mock(Scanner.class);
        view = new ChooseDifficultyView();

        view.setChooseColorView(chooseColorView);
        view.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("2");


        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            return null;
        }).when(chooseColorView).render();


        view.render();

        verify(chooseColorView, times(1)).render();
    }
}