package Controller;

import Module.DragHandler;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    @FXML
    private MaterialDesignIconView optionBtnIcon;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        someInitiations();
    }

    private void someInitiations() {
        //add drag
        Executors
                .newSingleThreadExecutor()
                .submit(() -> DragHandler.makeStageDragable(rootAnchorPane));

        //set button action
        exitBtn.setOnMouseReleased(event -> System.exit(0));
        minimizeBtn.setOnMouseReleased(event -> ((Stage) rootAnchorPane.getScene().getWindow()).setIconified(true));

        //rotate animation
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), optionBtnIcon);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setCycleCount(-1);

        //set rotate on hover
        optionBtn.setOnMouseEntered(event -> rotateTransition.play());
        optionBtn.setOnMouseExited(event -> rotateTransition.pause());
    }


}
