package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FarmerPrizeBoxTest {

    private FarmerPrizeBox farmerPrizeBox;
    private AbstractPlayer mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = Mockito.mock(HumanPlayer.class);
        when(mockPlayer.getMoneyAvailable()).thenReturn(2500);
        farmerPrizeBox = new FarmerPrizeBox();
    }

    @Test
    void testActionBox() {
        farmerPrizeBox.actionBox(mockPlayer);
        verify(mockPlayer, times(1)).receive(7500);
    }

    @Test
    void testAssuranceReceiveAmount() {
        when(mockPlayer.getMoneyAvailable()).thenReturn(10000);
        farmerPrizeBox.actionBox(mockPlayer);
        assertEquals(10000, mockPlayer.getMoneyAvailable());
    }

    @Test
    void testConstructorSetsValues() {
        FarmerPrizeBox box = new FarmerPrizeBox(1, 2, Collections.emptyList(), BoxType.FARMER_PRIZE);
        assertEquals(1, box.getIdBox());
        assertEquals(2, box.getBoxNumber());
        assertTrue(box.getPlayers().isEmpty());
        assertEquals(BoxType.FARMER_PRIZE, box.getBoxType());
    }
}
