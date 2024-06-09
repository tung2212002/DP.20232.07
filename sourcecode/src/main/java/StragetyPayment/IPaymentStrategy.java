package StragetyPayment;

import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public interface IPaymentStrategy {
    public PaymentTransaction payOrder(Card card, int amount, String contents);
}
