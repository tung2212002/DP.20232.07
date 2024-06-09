package subsystem.interbank;

import entity.payment.Card;
import entity.payment.PaymentTransaction;
import subsystem.interbank.extractingpaymenttransaction.ExtractingTransactionProvider;

/*
 * SOLID - Open/closed principle: InterbankPayloadConverter khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do payOrder(), refund() hiện tại chỉ nhận vào CreditCard
 */

/*
 * SOLID - Dependency inversion principle: InterbankPayloadConverter phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */

public class InterbankSubsystemController {

	private static InterbankPayloadConverter interbankPayloadConverter;
	private static InterbankBoundary interbankBoundary = new InterbankBoundary();


	public PaymentTransaction refund(Card card, int amount, String contents) {
		return null;
	}

	public PaymentTransaction payOrder(Card card, int amount, String contents) {
		interbankPayloadConverter = new InterbankPayloadConverter(ExtractingTransactionProvider.getExtractingPaymentTransaction(card));
		String requestPayload = interbankPayloadConverter.convertToRequestPayload(card, amount, contents);
		String responseText = interbankBoundary.query(InterbankConfigs.PROCESS_TRANSACTION_URL, requestPayload);
		return interbankPayloadConverter.extractPaymentTransaction(responseText);
	}

}
