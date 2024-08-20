package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.CardBox;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.DeckOfCard;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.services.BoxService;
import ar.edu.utn.frc.tup.lciii.services.CardService;
import ar.edu.utn.frc.tup.lciii.services.DashboardService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DashboardServiceImpl implements DashboardService {
	private final BoxService boxService;
	private final CardService cardService;

	public DashboardServiceImpl(BoxService boxService, CardService cardService) {
		this.boxService = boxService;
		this.cardService = cardService;
	}

	private static class Holder {
		private static DashboardServiceImpl INSTANCE;
	}

	public static DashboardServiceImpl getInstance(BoxService boxService, CardService cardService) {
		if (DashboardServiceImpl.Holder.INSTANCE == null) {
			DashboardServiceImpl.Holder.INSTANCE = new DashboardServiceImpl(boxService, cardService);
		}
		return DashboardServiceImpl.Holder.INSTANCE;
	}

	@Override
	public void createInitialDashboard() {
		List<AbstractBox> boxes = boxService.getBoxes();

		for (AbstractBox box : boxes) {
			if (box.getDescription().contains("Destiny")) {
				CardBox cardBox = (CardBox) box;
				cardBox.setIsDestiny(true);
			} else if (box.getDescription().contains("Lucky")) {
				CardBox cardBox = (CardBox) box;
				cardBox.setIsDestiny(false);
			}
		}

		DeckOfCard destinyDeck = new DeckOfCard();
		DeckOfCard luckyDeck = new DeckOfCard();

		Queue<AbstractCard> cards = cardService.getCards();

		LinkedList<AbstractCard> destinyCards = new LinkedList<>();
		LinkedList<AbstractCard> luckyCards = new LinkedList<>();

		for (AbstractCard card : cards) {
			if (card.getIsDestiny()) {
				destinyCards.add(card);
			} else {
				luckyCards.add(card);
			}
		}

		destinyDeck.setCards(destinyCards);
		luckyDeck.setCards(luckyCards);

		destinyDeck.shuffleDeck();
		luckyDeck.shuffleDeck();

		Dashboard.getInstance().setIdDashboard(0);
		Dashboard.getInstance().setBoxes(boxes);
		Dashboard.getInstance().setLuckyCards(luckyDeck);
		Dashboard.getInstance().setDestinyCards(destinyDeck);
	}

	@Override
	public void setPlayersIntoStartBox(Queue<AbstractPlayer> players) {
		StartBox box = (StartBox) Dashboard.getInstance().findBoxByType(StartBox.class);
		box.setPlayers(new ArrayList<>(players));
	}

	@Override
	public void setPlayersIntoTheirBoxes(Queue<AbstractPlayer> players) {
		for (AbstractPlayer player : players) {
			for (AbstractBox box : Dashboard.getInstance().getBoxes()) {
				if (box.getIdBox().equals(player.getIdBox())) {
					box.addPlayerToBox(player);
				}
			}
		}
	}
}
