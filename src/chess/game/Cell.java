package chess.game;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Saad
 */
public class Cell extends StackPane {

    Rectangle rect;
    Paint fill;
    String image;
    int x, y;

    public Cell(double width, double height, Paint fill, int x, int y) {
        this.fill = fill;
        this.x = x;
        this.y = y;
        rect = new Rectangle(width, height);
        rect.setStroke(Color.DARKCYAN);
        rect.setFill(fill);

        rect.setStyle("-fx-opacity: 0.7;");
        getChildren().add(rect);
    }

    //get row of the cell
    public int getX() {
        return x;
    }

    //get column of the cell
    public int getY() {
        return y;
    }

    public void setNullImage() {
        this.image = null;
    }

    public void setImage(String image) {
        this.image = image;
        System.out.println(image);
        Image omg = new Image(Cell.class.getResourceAsStream("pieces/" + image + ".png"));
        ImageView imageView = new ImageView();
        imageView.setImage(omg);
        imageView.setFitHeight(60);
        imageView.setFitWidth(40);
        getChildren().add(imageView);
    }

    public void setBackgroundColor() {
        rect.setFill(Color.TEAL);
    }

    public String getImage() {
        return image;
    }

    public Paint getFill() {
        return fill;
    }

    public void setFill(Paint fill) {
        this.fill = fill;
        this.rect.setFill(fill);
    }

}
