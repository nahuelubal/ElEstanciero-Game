package ar.edu.utn.frc.tup.lciii.interfaces.box;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;

public interface IBoxFactory {
    AbstractBox createBox(BoxType boxType);
}
