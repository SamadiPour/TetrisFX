import Controller.SplashScreenController;
import Module.DragHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * @author SamadiPour
 */
public class Launch extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        //display splash screen for 2 second with fade and blur effect
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent splahRoot = fxmlLoader.load(getClass().getResource("layout/SplashScreen.fxml").openStream());
        SplashScreenController splashScreen = fxmlLoader.getController();
        Scene splashScene = new Scene(splahRoot);
        splashScene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(splashScene);

        //check if splash screen is done
        //then we show main program
        splashScreen.isFinishedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                DragHandler.setStage(stage);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/layout/Main.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
