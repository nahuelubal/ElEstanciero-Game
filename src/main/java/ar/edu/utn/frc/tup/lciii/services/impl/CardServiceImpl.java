package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.card.CardEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.card.ICardRepository;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.CardType;
import ar.edu.utn.frc.tup.lciii.services.CardService;
import org.modelmapper.ModelMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardServiceImpl implements CardService {
	private final ICardRepository cardRepository;
	private final ModelMapper modelMapper;

	private CardServiceImpl(ICardRepository cardRepository, ModelMapper modelMapper) {
		this.cardRepository = cardRepository;
		this.modelMapper = modelMapper;
	}

	private static class Holder {
		private static CardServiceImpl INSTANCE;
	}

	public static CardServiceImpl getInstance(ICardRepository cardRepository, ModelMapper modelMapper) {
		if (CardServiceImpl.Holder.INSTANCE == null) {
			CardServiceImpl.Holder.INSTANCE = new CardServiceImpl(cardRepository, modelMapper);
		}
		return CardServiceImpl.Holder.INSTANCE;
	}

	@Override
	public Queue<AbstractCard> getCards() {
		LinkedList<AbstractCard> cards = new LinkedList<>();
		List<CardEntity> cardsEntities = cardRepository.findAll();

		for (CardEntity cardEntity : cardsEntities) {
			AbstractCard cardToAdd = modelMapper.map(cardEntity, CardFactoryImpl.getTypeOfCard(cardEntity.getCardType().getCardTypeName()));
			cards.add(cardToAdd);
		}

		return cards;
	}


}
