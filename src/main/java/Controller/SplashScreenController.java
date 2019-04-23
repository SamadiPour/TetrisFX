package Controller;

import animatefx.animation.AnimationFX;
import animatefx.animation.RotateInDownLeft;
import animatefx.animation.ZoomIn;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashScreenController implements Initializable {

    SimpleBooleanProperty isFinished = new SimpleBooleanProperty();

    public SimpleBooleanProperty isFinishedProperty() {
        return isFinished;
    }


    @FXML
    private ImageView splashScreen;
    @FXML
    private Text splashText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set cache setting
        splashScreen.setCache(true);
        splashScreen.setCacheHint(CacheHint.SPEED);
        splashText.setCache(true);
        splashText.setCacheHint(CacheHint.SPEED);
        splashText.setEffect(new Lighting());

        //init
        isFinished.set(false);
        splashText.setVisible(false);

        //animate fadein
        AnimationFX fadeIn = new ZoomIn(splashScreen).setSpeed(0.9);

        //animate blur effect
        GaussianBlur gaussianBlur = new GaussianBlur();
        gaussianBlur.setRadius(1);
        Timeline blurAnimation = new Timeline(
                new KeyFrame(Duration.millis(300)),
                new KeyFrame(Duration.millis(700),
                        new KeyValue(gaussianBlur.radiusProperty(), 50))
        );

        //text animation
        AnimationFX splashTextAnimation = new RotateInDownLeft(splashText).setSpeed(0.75);
        splashTextAnimation.setOnFinished(event -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isFinished.setValue(true);
        });

        //set what should be done after fadein
        fadeIn.setOnFinished(event -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashScreen.setEffect(gaussianBlur);
            blurAnimation.playFromStart();
        });

        //set what should be done after blur
        blurAnimation.setOnFinished(event -> {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> splashText.setVisible(true), 80, TimeUnit.MILLISECONDS);
            splashTextAnimation.play();
        });

        //lets start
        fadeIn.play();
    }
}
