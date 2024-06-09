package subsystem.interbank.extractingpaymenttransaction;


import common.interfaces.StrategyExtractingPaymentTransaction;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.DomesticCard;

public class ExtractingTransactionProvider {
    public static StrategyExtractingPaymentTransaction getExtractingPaymentTransaction(Card card){
        if(card instanceof CreditCard){
            return new ExtractingCreditCardTransaction();
        } else if (card instanceof DomesticCard) {
            return  new ExtractingDomesticCardTransaction();
        }

        throw new IllegalArgumentException("Unsupported card type");

    }
}
