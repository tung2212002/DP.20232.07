package subsystem.interbank.extractingpaymenttransaction;

import common.interfaces.StrategyExtractingPaymentTransaction;
import entity.payment.Card;
import entity.payment.CreditCard;
import utils.MyMap;

public class ExtractingCreditCardTransaction implements StrategyExtractingPaymentTransaction {

    public Card extractCardTransaction(MyMap transaction) {

        Card card = new CreditCard(
                (String) transaction.get("cardCode"),
                (String) transaction.get("owner"),
                (String) transaction.get("dateExpired"),
                Integer.parseInt((String) transaction.get("cvvCode")));

        return card;
    }
}
