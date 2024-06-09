package entity.payment;

/*
 * SOLID - Open/closed principle: PaymentTransaction khó mở rộng nếu trong tương lai có thêm phương thức thanh toán mới do hàm khởi tạo hiện tại chỉ nhận vào CreditCard
 */

/*
 * SOLID: Dependency inversion principle: PaymentTransaction phụ thuộc vào CreditCard ảnh hưởng đến việc mở rộng nếu có thêm phương thức thanh toán khác
 */
public class PaymentTransaction {
	private String errorCode;
	private Card card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	public PaymentTransaction(String errorCode, Card card, String transactionId, String transactionContent,
							  int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
