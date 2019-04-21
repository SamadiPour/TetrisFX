package Controller;

import Module.DragHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

/**
 * @author SamadiPour
 */
public class MainScreenController implements Initializable {

    /*---------------------------FXML Attributes-----------------------------*/
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXButton exitBtn;
    @FXML
    private JFXButton minimizeBtn;
    @FXML
    private JFXButton optionBtn;
    @FXML
    private AnchorPane topBarNav;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Executors
                .newSingleThreadExecutor()
                .submit(() -> DragHandler.makeStageDragable(rootAnchorPane));
        someInitiations();
    }

    private void someInitiations() {
        exitBtn.setOnMouseReleased(event -> System.exit(0));
        minimizeBtn.setOnMouseReleased(event -> ((Stage) rootAnchorPane.getScene().getWindow()).setIconified(true));
    }


}
