package subsystem;

import StragetyPayment.IPaymentStrategy;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 *
 * @author hieud
 *
 */

/*
 * SOLID - Open/closed principle: InterbankSubsystem khó mở rộng nếu trong tương
 * lai có thêm phương thức thanh toán mới do payOrder() và refund() chỉ nhận vào
 * CreditCard
 */

/*
 * SOLID - Dependency inversion principle: InterbankSubsystem phụ thuộc vào
 * CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */

public class InterbankSubsystem implements InterbankInterface, IPaymentStrategy {

	/**
	 * Represent the controller of the subsystem
	 */
	// Clean code: ctrl không rõ ràng, nên đặt tên rõ ràng hơn
	private InterbankSubsystemController ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem() {
		this.ctrl = new InterbankSubsystemController();
	}

	/**
	 * @see InterbankInterface#payOrder(Card, int,
	 *      String)
	 */
	public PaymentTransaction payOrder(Card card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(Card, int,
	 *      String)
	 */
	public PaymentTransaction refund(Card card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
