package ar.edu.utn.frc.tup.lciii.interfaces.deeds;

import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;

public interface IDeedFactory {
    AbstractDeed createDeed(DeedType deedType);
}
