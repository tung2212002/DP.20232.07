package StragetyPayment;

import entity.payment.CODTransaction;
import entity.payment.PaymentDetail;
import entity.payment.PaymentTransaction;

public class COD implements IPaymentStrategy{
    @Override
    public PaymentDetail payOrder(int amount, String contents) {
        CODTransaction detail = new CODTransaction(amount, contents);
        return detail;
    }
}
