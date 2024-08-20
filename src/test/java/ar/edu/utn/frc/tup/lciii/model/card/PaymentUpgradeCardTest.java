package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.objenesis.SpringObjenesis;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentUpgradeCardTest {

    @Mock
    AbstractPlayer player;
    @Mock
    Province province;
    PaymentUpgradeCard paymentUpgradeCard;
    @BeforeEach
    void setUp(){
        player = mock(AbstractPlayer.class);
        province = mock(Province.class);
        when(province.getFarmQuantity()).thenReturn(1);
        when(province.getDeedType()).thenReturn(DeedType.PROVINCE);
        when(player.getDeeds()).thenReturn(List.of(province));
        paymentUpgradeCard = new PaymentUpgradeCard();
    }
    @Test
    void actionCard() {
        paymentUpgradeCard.actionCard(player);
        verify(player , times(1)).pay(800);
    }

    @Test
    void actionCardTest() {
        when(province.getHasRanch()).thenReturn(true);
        paymentUpgradeCard.actionCard(player);
        verify(player , times(1)).pay(4800);
    }


}