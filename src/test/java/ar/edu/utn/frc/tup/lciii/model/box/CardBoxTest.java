package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.ChargeCard;
import ar.edu.utn.frc.tup.lciii.model.card.DeckOfCard;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardBoxTest {
    AbstractPlayer player;
    DeckOfCard deckOfCard;
    AbstractCard card;
    Dashboard dashboard;
    CardBox cardBox;

    @BeforeEach
    void setUp() {
        cardBox = new CardBox();
        cardBox.setIsDestiny(true);
        player = mock(AbstractPlayer.class);
        card = mock(ChargeCard.class);

        when(card.getDescription()).thenReturn("Carta ejemplo");
        doNothing().when(card).actionCard(player);
        dashboard = new Dashboard();
    }

    @Test
    void actionBoxWithNullCard() {
        try (MockedStatic<Dashboard> mockedDashboard = mockStatic(Dashboard.class)) {
            dashboard.setDestinyCards(new DeckOfCard());
            mockedDashboard.when(Dashboard::getInstance).thenReturn(dashboard);

            cardBox.actionBox(player);

            verify(card, never()).getDescription();
            verify(card, never()).actionCard(player);
        }
    }

    @Test
    void actionBoxWithCard() {
        try (MockedStatic<Dashboard> mockedDashboard = mockStatic(Dashboard.class)) {
            DeckOfCard deck = new DeckOfCard();
            Queue<AbstractCard> cards = new LinkedList<>();
            cards.add(card);
            deck.setCards(cards);
            dashboard.setDestinyCards(deck);
            mockedDashboard.when(Dashboard::getInstance).thenReturn(dashboard);

            when(card.getDescription()).thenReturn("Carta ejemplo");

            cardBox.actionBox(player);

            verify(card, times(1)).getDescription();
            verify(card, times(1)).actionCard(player);
;
        }
    }

    @Test
    void constructorTest(){
        CardBox cardTest = new CardBox(1,1,new ArrayList<>(),BoxType.CARD);
        assertEquals(cardTest.getIdBox(), 1);
        assertEquals(cardTest.getBoxType(), BoxType.CARD);
        assertTrue(cardTest.getPlayers().isEmpty());
    }
}