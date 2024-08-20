package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StartBoxTest {
    private StartBox startBox;
    private List<AbstractPlayer> players;

    @BeforeEach
    void setUp() {
        players = new ArrayList<>();
        players.add(mock(AbstractPlayer.class));
        startBox = new StartBox(1, 1, players, BoxType.START_BOX);
        startBox.setAmount(200);
    }

    @Test
    void testConstructor() {
        assertNotNull(startBox);
        assertEquals(1, startBox.getIdBox());
        assertEquals(1, startBox.getBoxNumber());
        assertEquals(players, startBox.getPlayers());
        assertEquals(BoxType.START_BOX, startBox.getBoxType());
    }

    @Test
    void testSetAndGetAmount() {
        startBox.setAmount(2000);
        assertEquals(2000, startBox.getAmount());
    }

    @Test
    void testActionBox() {
        AbstractPlayer player = mock(AbstractPlayer.class);
        StartBox startBox1 = mock(StartBox.class);
        startBox1.actionBox(player);
        verify(startBox1,times(1)).actionBox(player);
    }
}