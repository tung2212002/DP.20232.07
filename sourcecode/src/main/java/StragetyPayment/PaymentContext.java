package StragetyPayment;

import entity.payment.PaymentDetail;

public class PaymentContext {
    private IPaymentStrategy strategy;

    public PaymentContext(IPaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IPaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public PaymentDetail executePayment(int amount, String contents) {
        return strategy.payOrder(amount, contents);
    }

}