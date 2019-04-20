package Controller;

import Module.DragHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

/**
 * @author SamadiPour
 */
public class MainController implements Initializable {
    @FXML
    private Pane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Executors
                .newSingleThreadExecutor()
                .submit(() -> DragHandler.makeStageDragable(mainPane));


    }


}
