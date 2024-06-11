package views.screen.home;

import entity.media.Media;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MediaDetailHandler extends BaseScreenHandler {


    @FXML
    private Label mediaTitle;

    @FXML
    private ImageView img;

    @FXML
    private Text quantity;

    @FXML
    private Text price;

    @FXML
    private Text category;




    protected MediaDetailHandler(Stage stage, String screenPath, Media media) throws IOException, SQLException {
        super(stage, screenPath, media);

        mediaTitle.setText(media.getTitle());
        File file = new File(media.getImageURL());
        Image image = new Image(file.toURI().toString());

        img.setImage(image);
        quantity.setText(Integer.toString(media.getQuantity()));
        price.setText(Integer.toString(media.getQuantity()));
        category.setText(media.getCategory());
    }




    @Override
    protected void setupData(Object dto) throws Exception {

    }

    @Override
    protected void setupFunctionality() throws Exception {

    }
}
