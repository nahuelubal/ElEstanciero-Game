package ar.edu.utn.frc.tup.lciii.interfaces.player;

/**
 * Los jugadores pueden ser de dos tipos, usuario y bot. Tanto el usuario como el bot tienen los
 * mismos comportamientos, es decir, ambos compran y venden propiedades, mejoran provincias, negocian.
 * La diferencia radica en que el usuario toma las decisiones naturales pero el bot debe decidir a través
 * de ciertas condiciones. En conclusión, ambas clases deben saber que hacer pero cada una se debe
 * ocupar de cómo hacerlo. Para ello la interfaz IPlayer provee los métodos necesarios para los
 * comportamientos de los jugadores y los jugadores se encargan de implementarlos.
 */

import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;

public interface IPlayer {
    void buyProperty(AbstractDeed property);
    void addRanch(Province province);

    void addFarm(Province province,Integer quantity);

    void pay(int money);

    void receive(int money);

    void choosePropertyToMortgage();

    void setBankrupt();

    void mortgageProperty(AbstractDeed property);
}
