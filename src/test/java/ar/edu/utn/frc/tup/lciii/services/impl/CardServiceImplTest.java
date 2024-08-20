package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.card.CardEntity;
import ar.edu.utn.frc.tup.lciii.entities.card.CardTypeEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.ITaxRepository;
import ar.edu.utn.frc.tup.lciii.interfaces.card.ICardRepository;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.CardType;
import ar.edu.utn.frc.tup.lciii.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CardServiceImplTest {

    @Mock
    private ICardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    private List<CardEntity> cardEntities;

    @BeforeEach
    public void setUp() {
        cardRepository = mock(ICardRepository.class);
        ModelMapper modelMapper = new ModelMapper();

        CardTypeEntity cardType = new CardTypeEntity();
        cardType.setIdCardType(1);
        cardType.setCardTypeName(CardType.CHARGE);

        CardEntity card = new CardEntity();
        card.setIdCard(1);
        card.setCardType(cardType);
        card.setIsDestiny(false);
        card.setCardDescription("Test Card");
        card.setCardValue(100);
        card.setBox(null);

        cardEntities = List.of(card);
        when(cardRepository.findAll()).thenReturn(cardEntities);
        cardService = CardServiceImpl.getInstance(cardRepository,modelMapper);
    }

    @Test
    public void getCards() {
        Queue<AbstractCard> cards = cardService.getCards();
        assertNotNull(cards);
        assertFalse(cards.isEmpty());
    }
}