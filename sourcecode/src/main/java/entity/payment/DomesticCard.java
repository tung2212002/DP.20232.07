package entity.payment;


public class DomesticCard implements Card {
    protected String issuingBank;
    protected String cardNumber;
    protected String validFrom;
    protected String cardHolderName;

    public DomesticCard(String issuingBank, String cardNumber, String validFrom, String cardHolderName) {
        this.issuingBank = issuingBank;
        this.cardNumber = cardNumber;
        this.validFrom = validFrom;
        this.cardHolderName = cardHolderName;
    }
}
