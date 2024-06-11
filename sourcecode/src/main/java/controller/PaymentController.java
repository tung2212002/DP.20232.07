package controller;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import StragetyPayment.IPaymentStrategy;
import StragetyPayment.PaymentContext;
import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import common.interfaces.CardFactory;
import dto.payment.CardDTO;
import entity.cart.Cart;
import entity.payment.Card;
import entity.payment.CreditCard;
import entity.payment.PaymentDetail;
import entity.payment.PaymentTransaction;
import entity.payment.type.CardType;
import factory.payment.CardFactoryProvider;
import helper.CardValidator;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;

/**
 * This {@code PaymentController} class control the flow of the payment process
 * in our AIMS Software.
 *
 * @author hieud
 *
 */

/*
 * SOLID - Single responsibility principle: PaymentController đang thực hiện
 * nhiều hơn 1 nhiệm vụ đó là thanh toán và quản lý giỏ hàng và lấy ngày hết hạn
 * Có xem xét đưa xử lí giỏ hàng vào CartController, getExpirationDate() có thể
 * xử lí bằng việc tạo class CreditCardValidator
 */

/*
 * SOLID - Open/closed principle: PaymentController khó mở rộng nếu trong tương
 * lai có phương thức thanh toán với thông tin của phương thức thanh toán khác
 * như thẻ nội địa
 * Có xem xét tạo class CreditCardValidator để xử lí validate thẻ hoặc tạo
 * interface CreditCardValidator để implement trong trường hợp có thể các loại
 * thẻ khác có date khác nhau
 * Có thể xem xét tạo class Card để nhiều loại thẻ con như CreditCard,
 * DomesticDebitCard kế thừa
 */

/*
 * SOLID - Liskov substitution principle: PaymentController không cần sử dụng
 * các phương thức khác của BaseController
 */

/*
 * SOLID - Dependency inversion principle: PaymentController đang phụ thuộc vào
 * CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */

// Temporal cohesion: getExpirationDate() không liên quan đến class chỉ thực
// hiện theo thứ tự thời gian bởi việc thực hiện payOrder() sử dụng
// getExpirationDate()
// Temporal cohesion: emptyCart() không liên quan đến class
public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private Card card;

	private PaymentContext context;
	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;


	/**
	 * Pay order, and then return the result with a message.
	 *
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link Map Map} represent the payment result with a
	 *         message.
	 */
	public Map<String, String> payOrder(int amount, String contents, IPaymentStrategy strategy) {
		Map<String, String> result = new Hashtable<String, String>();
		result.put("RESULT", "PAYMENT FAILED!");
		try {

			this.interbank = new InterbankSubsystem();
			context.setStrategy(strategy);
			PaymentDetail transaction = context.executePayment(amount, contents);

			result.put("RESULT", "PAYMENT SUCCESSFUL!");
			result.put("MESSAGE", "You have successfully paid the order!");
		} catch (PaymentException | UnrecognizedException ex) {
			result.put("MESSAGE", ex.getMessage());
		}
		return result;
	}

	// Common coupling: emptyCart() sử dụng global data SessionInformation là
	// cartInstance

}
//Sử dụng strategy pattern để thực hiện thanh toán, giúp dễ mở rộng hơn khi có thêm phương thức thanh toán mới
//Chỉ cần thêm class mới implement IPaymentStrategy, khi thực hiện đổi phương thức thanh toán,
//gọi hàm SetStrategy() để chuyển sang phương thức thanh toán mới với tham số được truyên là một instance mới
//của class implement IPaymentStrategy phương thức thanh toán mới
