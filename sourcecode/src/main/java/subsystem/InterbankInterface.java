package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link InterbankSubsystem InterbankSubsystem} to make transaction
 *
 * @author hieud
 *
 */

/*
 * SOLID - Open/closed principle: InterbankInterface khó mở rộng nếu trong tương
 * lai có thêm phương thức thanh toán mới do payOrder() và refund() chỉ nhận vào
 * CreditCard
 */

/*
 * SOLID - Dependency inversion principle: InterbankInterface phụ thuộc vào
 * CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */

public interface InterbankInterface {

	/**
	 * Pay order, and then return the payment transaction
	 *
	 * @param card     - the credit card used for payment
	 * @param amount   - the amount to pay
	 * @param contents - the transaction contents
	 * @return {@link PaymentTransaction PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	public abstract PaymentTransaction payOrder(Card card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

	/**
	 * Refund, and then return the payment transaction
	 *
	 * @param card     - the credit card which would be refunded to
	 * @param amount   - the amount to refund
	 * @param contents - the transaction contents
	 * @return {@link PaymentTransaction PaymentTransaction} - if the
	 *         payment is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	public abstract PaymentTransaction refund(Card card, int amount, String contents)
			throws PaymentException, UnrecognizedException;

}
