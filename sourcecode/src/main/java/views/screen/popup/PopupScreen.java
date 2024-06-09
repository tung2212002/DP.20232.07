package views.screen.popup;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import views.screen.BaseScreenHandler;
import views.screen.ViewsConfig;

import java.io.IOException;

/*
 * SOLID - Liskov substitution principle: PopupScreen không cần triển khai các phương thức khác của BaseScreenHandler
 */

// Logical cohesion: success(), error(), loading() thực hiện các thao tác liên quan đến hiển thị popup

public class PopupScreen extends BaseScreenHandler {

    @FXML
    ImageView icon;

    @FXML
    Label message;

    // Common coupling: PopupScreen() sử dụng global data ViewsConfig là POPUP_PATH
    public PopupScreen(Stage stage) throws IOException {
        super(stage, ViewsConfig.POPUP_PATH, null);
    }

    private static PopupScreen popup(String message, String imagePath, Boolean undecorated) throws IOException {
        PopupScreen popup = new PopupScreen(new Stage());
        if (undecorated)
            popup.stage.initStyle(StageStyle.UNDECORATED);
        popup.message.setText(message);
        popup.setImage(imagePath);
        return popup;
    }

    // Common coupling: PopupScreen() sử dụng global data ViewsConfig là IMAGE_PATH
    public static void success(String message) throws IOException {
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickgreen.png", true)
                .show(true);
    }

    // Common coupling: PopupScreen() sử dụng global data ViewsConfig là IMAGE_PATH
    public static void error(String message) throws IOException {
        popup(message, ViewsConfig.IMAGE_PATH + "/" + "tickerror.png", false)
                .show(false);
    }

    // Common coupling: PopupScreen() sử dụng global data ViewsConfig là IMAGE_PATH
    public static PopupScreen loading(String message) throws IOException {
        return popup(message, ViewsConfig.IMAGE_PATH + "/" + "loading.gif", true);
    }

    public void setImage(String path) {
        super.setImage(icon, path);
    }

    public void show(Boolean autoClose) {
        super.show();
        if (autoClose)
            close(0.8);
    }

    public void show(double time) {
        super.show();
        close(time);
    }

    public void close(double time) {
        PauseTransition delay = new PauseTransition(Duration.seconds(time));
        delay.setOnFinished(event -> stage.close());
        delay.play();
    }

    // Stamp coupling: setupData() truyền vào dto nhưng không sử dụng đến
    @Override
    protected void setupData(Object dto) throws Exception {
    }

    @Override
    protected void setupFunctionality() throws Exception {
    }
}
