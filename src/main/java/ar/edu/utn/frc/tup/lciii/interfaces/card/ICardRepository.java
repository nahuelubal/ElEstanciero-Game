package ar.edu.utn.frc.tup.lciii.interfaces.card;

import ar.edu.utn.frc.tup.lciii.entities.card.CardEntity;

import java.util.List;

public interface ICardRepository {

    List<CardEntity> findAll();

}

