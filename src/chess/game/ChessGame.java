/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import insidefx.undecorator.Undecorator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
//        Undecorator undecorator = new Undecorator(primaryStage, root);
//
//        // Default theme
//        undecorator.getStylesheets().add("style.css");
//
//        try {
//            parent = FXMLLoader.load(getClass().getResource("DecoratedStage.fxml"));
//        } catch (IOException ex) {
//            Logger.getLogger(ChessGame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//

        root.setStyle("-fx-effect: dropshadow(gaussian, grey, " + shadowsize + ", 0, 0, 0);" +
                "-fx-background-insets: " + shadowsize + ";");

//        StackPane sp = new StackPane(root);
//        sp.setStyle(
//                "-fx-background-color: rgba(255, 255, 255, 0.5);" +
//                "-fx-background-insets: "+shadowsize+";"
//        );

//        Rectangle innerRect = new Rectangle();
//        Rectangle outerRect = new Rectangle();
//
//        sp.layoutBoundsProperty().addListener(
//                (observable, oldBounds, newBounds) -> {
//                    innerRect.relocate(
//                            newBounds.getMinX() + shadowsize,
//                            newBounds.getMinY() + shadowsize
//                    );
//                    innerRect.setWidth(newBounds.getWidth() - shadowsize * 2);
//                    innerRect.setHeight(newBounds.getHeight() - shadowsize * 2);
//
//                    outerRect.setWidth(newBounds.getWidth());
//                    outerRect.setHeight(newBounds.getHeight());
//
//                    Shape clip = Shape.subtract(outerRect, innerRect);
//                    sp.setClip(clip);
//                }
//        );

        Scene scene = new Scene(root, WIDTH, HEIGHT);

//        root.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");

        EffectUtilities.makeDraggable(primaryStage, root);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ChessGame.class.getResourceAsStream("pieces/blackknight.png")));
        primaryStage.show();

        // Transparent scene and stage
        scene.setFill(Color.TRANSPARENT);
    }

}
