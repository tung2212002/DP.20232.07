package subsystem.interbank.extractingpaymenttransaction;

import common.interfaces.StrategyExtractingPaymentTransaction;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.DomesticCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankPayloadConverter;
import utils.MyMap;

public class ExtractingDomesticCardTransaction implements StrategyExtractingPaymentTransaction {

    public Card extractCardTransaction(MyMap transaction) {

        Card card = new DomesticCard(
                (String) transaction.get("issuingBank"),
                (String) transaction.get("cardNumber"),
                (String) transaction.get("validFrom"),
                (String) transaction.get("cardHolderName"));

        return card;
    }
}
