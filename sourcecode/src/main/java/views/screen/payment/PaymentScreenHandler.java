package views.screen.payment;

import controller.PaymentController;
import dto.payment.CardDTO;
import dto.payment.CreditCardDTO;
import entity.cart.Cart;
import entity.invoice.Invoice;
import entity.payment.type.CardType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentScreenHandler extends BaseScreenHandler {

	private static final Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

	@FXML
	private Button btnConfirmPayment;

	@FXML
	private ImageView loadingImage;

	private Invoice invoice;

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;

	private CardType cardType;


	public PaymentScreenHandler(Stage stage, String screenPath, Invoice invoice) throws IOException {
		super(stage, screenPath, invoice);
	}

	@Override
	protected void setupData(Object dto) throws Exception {
		this.invoice = (Invoice) dto;
	}

	@Override
	protected void setupFunctionality() throws Exception {
		btnConfirmPayment.setOnMouseClicked(e -> {
			try {
				confirmToPayOrder();
				Cart.getInstance().emptyCart();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}

	// Common coupling: confirmToPayOrder sử dụng global data ViewsConfig là RESULT_SCREEN_PATH
	void confirmToPayOrder() throws IOException{
		String contents = "pay order";
		PaymentController ctrl = (PaymentController) getBController();
		this.cardType = CardType.CREDIT;
		CardDTO cardInfo = new CreditCardDTO(cardNumber.getText(), holderName.getText(), expirationDate.getText(), securityCode.getText());
		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, cardInfo, cardType);
		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, ViewsConfig.RESULT_SCREEN_PATH, response);
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}
}
