package StragetyPayment;

import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentDetail;
import entity.payment.PaymentTransaction;

public interface IPaymentStrategy {
    public PaymentDetail payOrder(int amount, String contents);
}
