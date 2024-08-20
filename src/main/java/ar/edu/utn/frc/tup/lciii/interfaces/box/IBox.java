package ar.edu.utn.frc.tup.lciii.interfaces.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;

import java.util.List;

/**
 * La interfaz de IBox representa el comportamiento de todas las casillas,
 * cada casilla sin importar su tipo acciona de alguna manera. Por lo tanto,
 * la interfaz ordena a todas las casillas a implementar una funcionalidad
 * cada vez que un jugador cae en la misma. Ejemplo: Tenemos 2 casillas,
 * una de tipo Provincia y otra de tipo cárcel. Cuando el jugador caiga en
 * cualquiera de las dos, solo bastara con llamar al método actionBox() de
 * la casilla para accionar sin consultar el tipo de dato de la misma.
 */
public interface IBox {
    void actionBox(AbstractPlayer player);
}
