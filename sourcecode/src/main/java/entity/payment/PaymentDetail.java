package entity.payment;

public abstract class PaymentDetail {
    protected String transactionContent;
    protected int amount;

    public PaymentDetail(String transactionContent, int amount) {
        this.transactionContent = transactionContent;
        this.amount = amount;
    }

    public PaymentDetail() {

    }
}
