package chess.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Saad
 */
public class Cell extends StackPane {

    private Rectangle rect;
    Paint fill;
    String image;
    private char bcase;
    int x, y;
    private int number=0; //pawn from 1 ==> 8  // bishop 9 , 10 // rook 11 , 12 //knight 13 , 14 // king 15 // queen 16

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

    public void setNonCharacter(){
        this.number = -1;
        this.image = null;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getBcase() {
        return bcase;
    }

    public void setBcase(char bcase) {
        this.bcase = bcase;
    }

    public boolean hasImage(){
        return this.image != null;
    }

}
