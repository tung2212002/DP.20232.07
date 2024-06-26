package views.screen.home;

import common.exception.MediaNotAvailableException;
import common.exception.ViewCartException;
import common.interfaces.Observable;
import common.interfaces.Observer;
import controller.AuthenticationController;
import controller.HomeController;
import controller.ViewCartController;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.cart.CartScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class LoginScreenHandler extends BaseScreenHandler{

    public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath, null);
    }

    public AuthenticationController getBController() {
        return (AuthenticationController) super.getBController();
    }

    // Stamp coupling: setupData() truyền vào dto nhưng không sử dụng đến
    @Override
    protected void setupData(Object dto) throws Exception {
    }

    @Override
    protected void setupFunctionality() throws Exception {
    }

    @FXML
    void login() throws IOException, InterruptedException, SQLException {
        try {
            getBController().login(email.getText(), password.getText());
            PopupScreen.success("Login Successfully!");
            backToHomeScreen();
        } catch (Exception ex) {
            PopupScreen.error(ex.getMessage());
        }
    }

    @FXML
    void backToHomeScreen() throws IOException, InterruptedException, SQLException {
        this.homeScreenHandler.show();
    }
}
