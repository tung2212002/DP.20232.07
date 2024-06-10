package entity.payment;

public class CODTransaction extends PaymentDetail {
    public CODTransaction(int amount, String contents) {
        super(contents, amount);
    }
}
