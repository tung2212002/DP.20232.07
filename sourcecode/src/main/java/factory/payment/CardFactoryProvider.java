package factory.payment;

import common.interfaces.CardFactory;
import entity.payment.type.CardType;


public class CardFactoryProvider {
    public static CardFactory getFactory(CardType cardType) {
        switch (cardType) {
            case CREDIT:
                return new CreditCardFactory();
            case DOMESTIC:
                return new DomesticCardFactory();
            default:
                throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
    }
}
