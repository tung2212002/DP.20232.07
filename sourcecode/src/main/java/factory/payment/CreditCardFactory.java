package factory.payment;

import common.interfaces.CardFactory;
import dto.payment.CreditCardDTO;
import entity.payment.Card;
import entity.payment.CreditCard;
import helper.CardValidator;

public class CreditCardFactory implements CardFactory<CreditCardDTO> {

    @Override
    public Card create(CreditCardDTO cardInfo) {

        return new CreditCard(
                cardInfo.getCardCode(),
                cardInfo.getOwner(),
                CardValidator.getExpirationDate(cardInfo.getDateExpired()),
                Integer.parseInt(cardInfo.getCvvCode())
        );
    }

}
