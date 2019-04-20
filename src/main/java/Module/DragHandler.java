package Module;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author SamadiPour
 */
public class DragHandler {

    private static Stage stage;
    private static double xOffSet = 0;
    private static double yOffSet = 0;

    public static void setStage(Stage stage) {
        DragHandler.stage = stage;
    }

    public static void makeStageDragable(Node parent) {
        parent.setOnMousePressed(event -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffSet);
            stage.setY(event.getScreenY() - yOffSet);
            changeOpacityWithAnimation(0.8f);
        });
        parent.setOnDragDone(e -> changeOpacityWithAnimation(1.0f));
        parent.setOnMouseReleased(e -> changeOpacityWithAnimation(1.0f));
    }

    private static void changeOpacityWithAnimation(float to) {
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(250),
                        new KeyValue(stage.opacityProperty(), to))
        );
        animation.playFromStart();
    }
}
