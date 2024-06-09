package subsystem.interbank;

import common.exception.*;
import common.interfaces.StrategyExtractingPaymentTransaction;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import helper.DateHelper;
import utils.MyMap;

import java.util.Map;

/**
 * @author
 */

/*
 * SOLID - Open/closed principle: InterbankPayloadConverter khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do convertToRequestPayload(),  hiện tại chỉ nhận vào CreditCard và extractPaymentTransaction() hiện tại chỉ xử lí cho 1 kiểu response của CreditCard
 */

/*
 * SOLID - Dependency inversion principle: InterbankPayloadConverter phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */

// Coincidental cohesion: getToday không liên quan đến class

public class InterbankPayloadConverter {

    private StrategyExtractingPaymentTransaction strategyExtractingPaymentTransaction;

    public InterbankPayloadConverter(StrategyExtractingPaymentTransaction strategyExtractingPaymentTransaction){
        this.strategyExtractingPaymentTransaction = strategyExtractingPaymentTransaction;
    }

    /**
     * Convert from native entity into interbank required format
     *
     * @param card
     * @param amount
     * @param contents
     * @return
     */
    String convertToRequestPayload(Card card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new InvalidCardException();
        }
        transaction.put("command", InterbankConfigs.PAY_COMMAND);
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", DateHelper.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", InterbankConfigs.VERSION);
        requestMap.put("transaction", transaction);

        return ((MyMap) requestMap).toJSON();
    }

    /**
     * Read the response from interbank server
     *
     * @param responseText
     * @return
     */
    // Control coupling: extractPaymentTransaction() sử dụng switch case với các
    // case là các mã lỗi chưa hợp lý
    PaymentTransaction extractPaymentTransaction(String responseText) {
        MyMap response = convertJSONResponse(responseText);

        if (response == null)
            return null;
        MyMap transaction = (MyMap) response.get("transaction");

        Card card = strategyExtractingPaymentTransaction.extractCardTransaction(transaction);

        PaymentTransaction trans = new PaymentTransaction(
                (String) response.get("errorCode"),
                card,
                (String) transaction.get("transactionId"),
                (String) transaction.get("transactionContent"),
                Integer.parseInt((String) transaction.get("amount")),
                (String) transaction.get("createdAt"));

        switch (trans.getErrorCode()) {
            case "00":
                break;
            case "01":
                throw new InvalidCardException();
            case "02":
                throw new NotEnoughBalanceException();
            case "03":
                throw new InternalServerErrorException();
            case "04":
                throw new SuspiciousTransactionException();
            case "05":
                throw new NotEnoughTransactionInfoException();
            case "06":
                throw new InvalidVersionException();
            case "07":
                throw new InvalidTransactionAmountException();
            default:
                throw new UnrecognizedException();
        }

        return trans;
    }

    /**
     * Convert response from interbank server as JSON-formatted String into a proper
     * Map
     *
     * @param responseText
     * @return
     */
    public static MyMap convertJSONResponse(String responseText) {
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new UnrecognizedException();
        }
        return response;
    }


}
