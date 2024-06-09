package dto.payment;

public class DomesticCardDTO implements CardDTO{

        private String issuingBank;
        private String cardNumber;
        private String validFrom;
        private String cardHolderName;

        public DomesticCardDTO(String issuingBank, String cardNumber, String validFrom, String cardHolderName) {
            this.issuingBank = issuingBank;
            this.cardNumber = cardNumber;
            this.validFrom = validFrom;
            this.cardHolderName = cardHolderName;
        }

        public String getIssuingBank() {
            return issuingBank;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public String getValidFrom() {
            return validFrom;
        }

        public String getCardHolderName() {
            return cardHolderName;
        }

}
