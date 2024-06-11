package views.screen.intro;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;
import views.screen.popup.PopupScreen;

/*
 * SOLID - Liskov substitution principle: IntroScreenHandler không cần thiết triển khai một số phương thức của BaseScreenHandler như setPreviousScreen
 */

/*
* Clean code: tên đường đến logo nên được lưu trong một biến static final LOGO_PATH của ViewsConfig
 */

public class IntroScreenHandler extends BaseScreenHandler {

    private static final Logger LOGGER = Utils.getLogger(IntroScreenHandler.class.getName());

    @FXML
    ImageView logo;

    public IntroScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath, null);
    }

    // Stamp coupling: setupData() truyền vào dto nhưng không sử dụng đến
    @Override
    protected void setupData(Object dto) throws Exception {
        return;
    }

    @Override
    protected void setupFunctionality() throws Exception {
        File file = new File(ViewsConfig.LOGO_PATH);
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}