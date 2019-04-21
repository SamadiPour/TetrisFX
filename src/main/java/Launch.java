import Controller.SplashScreenController;
import Module.DragHandler;
import animatefx.animation.AnimationFX;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author SamadiPour
 */
public class Launch extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Platform.setImplicitExit(false);
        stage.getIcons().add(new Image(Launch.class.getResourceAsStream("images/icon.png")));
        stage.setTitle("TetrisFX");


        //display splash screen for 2 second with fade and blur effect
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent splashRoot = fxmlLoader.load(getClass().getResource("layout/SplashScreen.fxml").openStream());
        SplashScreenController splashScreen = fxmlLoader.getController();

        Stage splashStage = new Stage();
        Scene splashScene = new Scene(splashRoot);
        splashScene.setFill(Color.TRANSPARENT);
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.setScene(splashScene);
        splashStage.getIcons().add(new Image(Launch.class.getResourceAsStream("images/icon.png")));
        splashStage.setTitle("TetrisFX");


        //check if splash screen is done
        //then we show main program
        splashScreen.isFinishedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                //make it draggable
                DragHandler.setStage(stage);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/layout/MainScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();

                //some animation
                new FadeOut(splashRoot).setSpeed(0.5).play();
                AnimationFX stageAnimation =  new FadeIn(root).setSpeed(1.6);
                stageAnimation.setOnFinished(event -> splashStage.close());
                stageAnimation.play();
                root.setVisible(false);

                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                Parent finalRoot = root;
                scheduler.schedule(() -> finalRoot.setVisible(true), 50, TimeUnit.MILLISECONDS);
            }
        });

        //start app
//        splashStage.show();
        splashScreen.isFinishedProperty().set(true);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
