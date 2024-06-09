package dto.payment;

public class CreditCardDTO implements CardDTO {
    private String cardCode;
    private String owner;
    private String dateExpired;
    private String cvvCode;

    public CreditCardDTO(String cardCode, String owner, String dateExpired, String cvvCode) {
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpired = dateExpired;
        this.cvvCode = cvvCode;
    }

    public String getCardCode() {
        return cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public String getCvvCode() {
        return cvvCode;
    }
}
