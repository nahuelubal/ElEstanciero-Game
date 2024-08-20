package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TaxBoxTest {
    private TaxBox taxBox;
    private ConsolePrinter consolePrinter;
    @Mock
    private AbstractPlayer mockPlayer;

    @BeforeEach
    void setUp() {
        taxBox = new TaxBox();
        mockPlayer = Mockito.mock(AbstractPlayer.class);
        consolePrinter = mock(ConsolePrinter.class);
    }

    @Test
    void actionBox() {
        taxBox.setTaxAmount(2000);
        taxBox.actionBox(mockPlayer);
        verify(mockPlayer, times(1)).pay(2000);
    }
    @Test
    void noArgsConstructor(){
        assertEquals(0,taxBox.getIdBox());
        assertEquals(0,taxBox.getBoxNumber());
        assertEquals(new ArrayList<AbstractPlayer>(),taxBox.getPlayers());

    }
    @Test
    void allArgsConstructor(){
        TaxBox taxBox1 = new TaxBox(1,1,new ArrayList<AbstractPlayer>(),BoxType.TAX_BOX);
        assertEquals(1,taxBox1.getIdBox());
        assertEquals(1,taxBox1.getBoxNumber());
        assertEquals(new ArrayList<AbstractPlayer>(),taxBox1.getPlayers());
        assertEquals(BoxType.TAX_BOX,taxBox1.getBoxType());

    }
}