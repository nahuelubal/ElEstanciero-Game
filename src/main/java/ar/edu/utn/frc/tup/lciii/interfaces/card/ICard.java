package ar.edu.utn.frc.tup.lciii.interfaces.card;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;

/**
 * La interfaz de ICard representa el comportamiento de todas las tarjetas tanto de destino como suerte.
 * Ambos tipos de tarjetas pueden obligar al jugador a pagar al banco, cobrar, moverse, ir a la cárcel,
 * etc. Cuando un jugador deba tomar una tarjeta, esta misma accionará su comportamiento sin consultar
 * su tipo de tarjeta.
 */
public interface ICard {
    void actionCard(AbstractPlayer player);
}
