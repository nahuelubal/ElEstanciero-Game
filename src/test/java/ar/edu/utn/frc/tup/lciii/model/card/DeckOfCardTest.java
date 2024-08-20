package ar.edu.utn.frc.tup.lciii.model.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DeckOfCardTest {

    private DeckOfCard deckOfCard;
    private AbstractCard mockCard1;
    private AbstractCard mockCard2;
    private AbstractCard mockCard3;

    @BeforeEach
    void setUp() {
        deckOfCard = new DeckOfCard();
        mockCard1 = Mockito.mock(AbstractCard.class);
        mockCard2 = Mockito.mock(AbstractCard.class);
        mockCard3 = Mockito.mock(AbstractCard.class);
    }

//    @Test
//    void testShuffleDeck() {
//
//        deckOfCard.getCards().add(mockCard1);
//        deckOfCard.getCards().add(mockCard2);
//        deckOfCard.getCards().add(mockCard3);
//
//        List<AbstractCard> initialOrder = new ArrayList<>(deckOfCard.getCards());
//
//        deckOfCard.shuffleDeck();
//
//        List<AbstractCard> shuffledOrder = new ArrayList<>(deckOfCard.getCards());
//
//        assertNotEquals(initialOrder, shuffledOrder);
//
//    }

    @Test
    void testTakeCard() {
        deckOfCard.getCards().add(mockCard1);
        AbstractCard takenCard = deckOfCard.takeCard();

        assertEquals(mockCard1, takenCard);
        assertTrue(deckOfCard.getCards().isEmpty());

        assertTrue(deckOfCard.getTransitionalCards().contains(mockCard1));
    }

    @Test
    void testPutCard() {
        deckOfCard.getCards().add(mockCard1);
        deckOfCard.takeCard();
        assertTrue(deckOfCard.getCards().isEmpty());

        deckOfCard.putCard(mockCard1);

        assertTrue(deckOfCard.getCards().contains(mockCard1));

        assertFalse(deckOfCard.getTransitionalCards().contains(mockCard1));
    }
    @Test
    void testPutHabeasCorpus() {
        deckOfCard.getTransitionalCards().add(mockCard1);
        deckOfCard.putHabeasCorpus();

        assertTrue(deckOfCard.getCards().contains(mockCard1));
        assertFalse(deckOfCard.getTransitionalCards().contains(mockCard1));
    }

    @Test
    void testTakeCard_EmptyDeck() {
        AbstractCard takenCard = deckOfCard.takeCard();
        assertNull(takenCard);
    }

    @Test
    void testCtor(){
        assertEquals(new LinkedList<>(),deckOfCard.getCards());
        assertEquals(new ArrayList<>(),deckOfCard.getTransitionalCards());

    }

}