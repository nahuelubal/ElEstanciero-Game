package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.box.*;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.interfaces.box.IBoxFactory;

public class BoxFactoryImpl {
    public static AbstractBox createBox(BoxType boxType) {
        return switch (boxType) {
            case CARD -> new CardBox();
            case COMPANY_PROPERTY -> new CompanyProperty();
            case PROVINCE_PROPERTY -> new ProvinceProperty();
            case RAILWAY_PROPERTY -> new RailwayProperty();
            case FARMER_PRIZE -> new FarmerPrizeBox();
            case GO_TO_JAIL -> new GoToJailBox();
            case JAIL_BOX -> new JailBox();
            case REST_BOX -> new RestBox();
            case START_BOX -> new StartBox();
            case TAX_BOX -> new TaxBox();
            default -> null;
        };
    }
    public static Class<? extends AbstractBox> getTypeOfBox(BoxType boxType) {
        return switch (boxType) {
            case CARD -> CardBox.class;
            case COMPANY_PROPERTY ->  CompanyProperty.class;
            case PROVINCE_PROPERTY ->  ProvinceProperty.class;
            case RAILWAY_PROPERTY ->  RailwayProperty.class;
            case FARMER_PRIZE ->  FarmerPrizeBox.class;
            case GO_TO_JAIL ->  GoToJailBox.class;
            case JAIL_BOX ->  JailBox.class;
            case REST_BOX ->  RestBox.class;
            case START_BOX ->  StartBox.class;
            case TAX_BOX ->  TaxBox.class;
            case FREE_PARKING ->  FreeParkingBox.class;
            default -> null;
        };
    }
}
