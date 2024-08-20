package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.card.*;

public class CardFactoryImpl {

    public static AbstractCard createCard(CardType cardType) {
        return switch (cardType) {
            case MOVE_BY_BOX -> new MovementBoxCard();
            case MOVE_BY_QUANTITY -> new MovementQuantityCard();
            case CHARGE -> new ChargeCard();
            case PAY_UPGRADE -> new PaymentUpgradeCard();
            case PAY -> new PaymentCard();
            default -> throw new IllegalArgumentException("Unknown card type: " + cardType);
        };
    }

    public static Class<? extends AbstractCard> getTypeOfCard(CardType cardType) {
        return switch (cardType) {
            case PAY -> PaymentCard.class;
            case MOVE_BY_QUANTITY -> MovementQuantityCard.class;
            case MOVE_BY_BOX -> MovementBoxCard.class;
            case PAY_UPGRADE -> PaymentUpgradeCard.class;
            case CHARGE -> ChargeCard.class;
            default -> PaymentCard.class;
        };
    }
}
