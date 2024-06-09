package common.interfaces;

import entity.payment.Card;
import utils.MyMap;

public interface StrategyExtractingPaymentTransaction {
    public Card extractCardTransaction(MyMap transaction);
}
