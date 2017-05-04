/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Saad
 */
public class ChessGame{

    public static Stage primaryStage ;

    public static double HEIGHT = 600;
    public static double WIDTH = 600;

    int shadowsize=600;

    public void showMainStage() {

        primaryStage = new Stage();
        Board root = new Board();

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        root.setStyle("-fx-effect: dropshadow(gaussian, grey, " + shadowsize + ", 0, 0, 0);" +
                "-fx-background-insets: " + shadowsize + ";");

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        EffectUtilities.makeDraggable(primaryStage, root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ChessGame.class.getResourceAsStream("pieces/blackknight.png")));
        primaryStage.show();

        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
    }

}
