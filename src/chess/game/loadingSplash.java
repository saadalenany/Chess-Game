package chess.game;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Saad
 */
public class loadingSplash {

    Pane splashPane;

    public void showLoadingSplash(){
        Stage primaryStage = new Stage();
        Image i = new Image(loadingSplash.class.getResourceAsStream("pieces/splash.gif"));
        ImageView splash = new ImageView(i);
        splash.setFitHeight(300);
        splash.setFitWidth(300);

        splash.setStyle("-fx-background-color:transparent");

        splashPane = new Pane();
        splashPane.getChildren().addAll(splash);
        splashPane.setEffect(new DropShadow());

        Scene splashScene = new Scene(splashPane);

        primaryStage.initStyle(StageStyle.UNDECORATED);

        Rectangle2D bounds = Screen.getPrimary().getBounds();

        primaryStage.setScene(splashScene);

        // Set Splash Screeen in the Center of Screen 
        primaryStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - 300 / 2);
        primaryStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - 300 / 2);

        primaryStage.show();
        primaryStage.getIcons().add(new Image(ChessGame.class.getResourceAsStream("pieces/blackknight.png")));

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), splashPane);
                fadeSplash.setFromValue(10.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished((ActionEvent actionEvent) -> {
                    primaryStage.hide();
                    new ChessGame().showMainStage();
                });
                fadeSplash.play();
                timer.cancel();
            }
        };
        timer.scheduleAtFixedRate(task, 3000, 100);

//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        splashScene.setFill(Color.TRANSPARENT);
    }

}
