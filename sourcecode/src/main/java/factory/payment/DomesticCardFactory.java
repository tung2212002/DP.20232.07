package factory.payment;

import common.interfaces.CardFactory;
import dto.payment.DomesticCardDTO;
import entity.payment.Card;
import entity.payment.DomesticCard;
import helper.CardValidator;

public class DomesticCardFactory implements CardFactory<DomesticCardDTO> {

    @Override
    public Card create(DomesticCardDTO cardInfo) {

        return new DomesticCard(
                cardInfo.getIssuingBank(),
                cardInfo.getCardHolderName(),
                CardValidator.getExpirationDate(cardInfo.getValidFrom()),
                cardInfo.getCardNumber()
        );
    }
}
