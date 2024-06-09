package StragetyPayment;

import StragetyPayment.IPaymentStrategy;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class PaymentContext {
    private IPaymentStrategy strategy;

    public PaymentContext(IPaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IPaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PaymentTransaction executePayment(Card card, int amount, String contents) {
        return strategy.payOrder(card, amount, contents);
    }

}