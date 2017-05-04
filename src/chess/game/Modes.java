package chess.game;

import chess.game.ChessGame;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Saad
 */
public class Modes {

    public static int mode;

    public void showModes(){
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        BorderPane p = new BorderPane();

        HBox root = new HBox();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setSpacing(10);

        Button onePlayer , twoPlayer;

        onePlayer = new Button("One Player");
        twoPlayer = new Button("Two Players");

        onePlayer.setStyle("-fx-background-color: \n" +
"        rgba(0,0,0,0.08),\n" +
"        linear-gradient(#9a9a9a, #909090),\n" +
"        linear-gradient(white 0%, #f3f3f3 50%, #ececec 51%, #f2f2f2 100%);\n" +
"    -fx-background-insets: 0 0 -1 0,0,1;\n" +
"    -fx-background-radius: 5,5,4;\n" +
"    -fx-padding: 3 30 3 30;\n" +
"    -fx-text-fill: #242d35;\n" +
"    -fx-font-size: 14px;");

        twoPlayer.setStyle("-fx-background-color: \n" +
"        rgba(0,0,0,0.08),\n" +
"        linear-gradient(#9a9a9a, #909090),\n" +
"        linear-gradient(white 0%, #f3f3f3 50%, #ececec 51%, #f2f2f2 100%);\n" +
"    -fx-background-insets: 0 0 -1 0,0,1;\n" +
"    -fx-background-radius: 5,5,4;\n" +
"    -fx-padding: 3 30 3 30;\n" +
"    -fx-text-fill: #242d35;\n" +
"    -fx-font-size: 14px;");

        root.getChildren().addAll(onePlayer,twoPlayer);

        onePlayer.setOnAction(e -> {
            primaryStage.hide();
            new loadingSplash().showLoadingSplash();
            mode = 1;
        });

        twoPlayer.setOnAction(e -> {
            primaryStage.hide();
            new loadingSplash().showLoadingSplash();
            mode = 2;
        });

        p.setCenter(root);
        root.setStyle("-fx-background-color:rgba(0,0,0,0.5);");
        p.setStyle("-fx-effect: dropshadow(gaussian, blue, 70, 0, 0, 0);" +
                "-fx-background-insets: 70;");

        Scene scene = new Scene(p, 325, 70);
        scene.setFill(Color.TRANSPARENT);

        p.prefHeightProperty().bind(scene.heightProperty());
        p.prefWidthProperty().bind(scene.widthProperty());

        EffectUtilities.makeDraggable(primaryStage, p);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setFullScreen(true);
        primaryStage.show();
        primaryStage.getIcons().add(new Image(ChessGame.class.getResourceAsStream("pieces/blackknight.png")));

    }
}
