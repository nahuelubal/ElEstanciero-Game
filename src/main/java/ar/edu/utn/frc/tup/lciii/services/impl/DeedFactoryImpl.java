package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.deeds.*;
import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeedFactory;

public class DeedFactoryImpl implements IDeedFactory {
    @Override
    public AbstractDeed createDeed(DeedType deedtype) {
        return switch (deedtype) {
            case COMPANY -> new Company();
            case PROVINCE -> new Province();
            case RAILWAY -> new Railway();
            default -> null;
        };
    }
}
