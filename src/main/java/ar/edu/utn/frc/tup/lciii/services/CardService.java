package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;

import java.util.Queue;

public interface CardService {
    public Queue<AbstractCard> getCards();
}
