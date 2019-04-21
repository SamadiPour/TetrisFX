package Controller;

import Module.DragHandler;
import Module.ShakeTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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
    private JFXButton helpBtn;
    @FXML
    private JFXButton optionBtn;
    @FXML
    private AnchorPane topBarNav;
    @FXML
    private MaterialDesignIconView optionBtnIcon;
    @FXML
    private MaterialDesignIconView helpButtonIcon;
    @FXML
    private StackPane rootStackPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add drag
        Executors
                .newSingleThreadExecutor()
                .submit(() -> DragHandler.makeStageDragable(rootAnchorPane));

        someButtonInitiations();
    }

    private void someButtonInitiations() {
        //set button action
        exitBtn.setOnMouseReleased(event -> System.exit(0));
        minimizeBtn.setOnMouseReleased(event -> ((Stage) rootAnchorPane.getScene().getWindow()).setIconified(true));
        helpBtn.setOnMouseClicked(event -> new ShakeTransition(helpBtn, 2, null).playFromStart());

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

    @FXML
    void helpButtonClicked(MouseEvent event) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        ImageView imageView = new ImageView("images/help.png");
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-background-color: transparent");
        dialogLayout.setBody(imageView);
        JFXDialog dialog = new JFXDialog(rootStackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton ok = new JFXButton("OK");
        ok.setButtonType(JFXButton.ButtonType.RAISED);
        ok.getStyleClass().add("green-button");
        dialogLayout.setActions(ok);
        ok.setOnMouseClicked(event1 -> dialog.close());
        dialog.show();
    }


}
