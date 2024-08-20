package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewGameViewTest {

    @Mock
    private ChooseDifficultyView chooseDifficultyView;

    @Mock
    private Scanner scanner;

    private NewGameView view;


    @Test
    void render() {
        chooseDifficultyView = mock(ChooseDifficultyView.class);
        scanner = mock(Scanner.class);
        view = new NewGameView();

        view.setChooseDifficultyView(chooseDifficultyView);
        view.setScanner(scanner);


        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            when(scanner.nextLine()).thenReturn("2");
            return null;
        }).when(chooseDifficultyView).render();


        view.render();

        verify(chooseDifficultyView, times(1)).render();
    }
}