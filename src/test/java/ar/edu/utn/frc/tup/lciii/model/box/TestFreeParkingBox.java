package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.others.console_writer.LetterByLetterPrinter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class TestFreeParkingBox {
    @Mock
    AbstractPlayer abstractPlayer;

    @Test
    void actionBox() {
        abstractPlayer = Mockito.mock(AbstractPlayer.class);

        FreeParkingBox freeParkingBox = new FreeParkingBox();

        assertDoesNotThrow(() -> freeParkingBox.actionBox(abstractPlayer));
    }
}