package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChooseColorViewTest {

    @Mock
    Scanner scanner;

    @Mock
    DefineMoneyToWinView defineMoneyToWinView;
   @Mock
    Game game;
    @InjectMocks
    ChooseColorView chooseColorView;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        game = Game.getInstance();
        game.setPlayers(new LinkedList<>());
    }
    @Test
    void render() {
        scanner = mock(Scanner.class);
        defineMoneyToWinView=mock(DefineMoneyToWinView.class);

        chooseColorView = new ChooseColorView();

        chooseColorView.setDefineMoneyToWinView(defineMoneyToWinView);
        chooseColorView.setScanner(scanner);

        when(scanner.nextLine()).thenReturn("4");

        doAnswer((Answer<Void>) invocation -> {
            System.out.println("Performing action!");
            return null;
        }).when(defineMoneyToWinView).render();


        chooseColorView.render();

        verify(defineMoneyToWinView, times(1)).render();
        assertEquals(1, game.getPlayers().size());
    }
}