package views.screen.payment;

import StragetyPayment.IPaymentStrategy;
import StragetyPayment.PaymentContext;
import common.interfaces.CardFactory;
import controller.PaymentController;
import dto.payment.CardDTO;
import dto.payment.CreditCardDTO;
import entity.cart.Cart;
import entity.invoice.Invoice;
import entity.payment.type.CardType;
import factory.payment.CardFactoryProvider;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import subsystem.InterbankSubsystem;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;
import entity.payment.Card;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentScreenHandler extends BaseScreenHandler {

	private static final Logger LOGGER = Utils.getLogger(PaymentScreenHandler.class.getName());

	@FXML
	private Button btnConfirmPayment;

	@FXML
	private RadioButton creditCard;

	@FXML
	private RadioButton cod;

	@FXML
	private ImageView loadingImage;

	private Invoice invoice;

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private VBox credit;

	@FXML
	private VBox domestic;

	@FXML
	private VBox cardInfo;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private RadioButton domesticCard;

	@FXML
	private ToggleGroup paymentMethod;
	@FXML
	private TextField securityCode;

	@FXML
	private TextField validFrom;

	@FXML
	private TextField issueBank;

	@FXML
	private ToggleGroup paymentMethod;

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
<<<<<<< Updated upstream

		paymentMethod.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			System.out.println("Selected: " + newValue);
			if (newValue == creditCard) {
				cardInfo.setDisable(false);
				cardInfo.setVisible(true);
				domestic.setVisible(false);
				credit.setVisible(true);
			} else if (newValue == cod) {
				cardInfo.setDisable(true);
			} else if (newValue == domesticCard) {
				cardInfo.setDisable(false);
				cardInfo.setVisible(true);
				domestic.setVisible(true);
				credit.setVisible(false);
=======
		paymentMethod.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == creditCard) {
				cardInfo.setVisible(true);
				creditCard.setVisible(true);
				domestic.setVisible(false);
			} else if (newValue == cod) {
				cardInfo.setVisible(false);
				creditCard.setVisible(false);
				domestic.setVisible(false);
			} else if (newValue == domesticCard)
			{
				cardInfo.setVisible(true);
				creditCard.setVisible(false);
				domestic.setVisible(true);
>>>>>>> Stashed changes
			}
		});
	}

	// Common coupling: confirmToPayOrder sử dụng global data ViewsConfig là RESULT_SCREEN_PATH
	void confirmToPayOrder() throws IOException{
		String contents = "pay order";
		PaymentController ctrl = (PaymentController) getBController();
		this.cardType = CardType.CREDIT;
		CardDTO cardInfo = new CreditCardDTO(cardNumber.getText(), holderName.getText(), expirationDate.getText(), securityCode.getText());
		CardFactory factory = CardFactoryProvider.getFactory(cardType);
		Card card = factory.create(cardInfo);
		InterbankSubsystem interbank = new InterbankSubsystem(card);
		Map<String, String> response = ctrl.payOrder(invoice.getAmount(), contents, interbank);
		BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, ViewsConfig.RESULT_SCREEN_PATH, response);
		resultScreen.setPreviousScreen(this);
		resultScreen.setHomeScreenHandler(homeScreenHandler);
		resultScreen.setScreenTitle("Result Screen");
		resultScreen.show();
	}

	private void SetCredit() {
		System.out.println("SetCredit");
	}
}
