/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import chess.game.AI.Checker;
import chess.game.Controllers.Bishop;
import chess.game.Controllers.BlackPawn;
import chess.game.Controllers.King;
import chess.game.Controllers.Knight;
import chess.game.Controllers.Queen;
import chess.game.Controllers.Rook;
import chess.game.Controllers.WhitePawn;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static chess.game.ref.*;

/**
 *
 * @author Saad
 */
public class Board extends GridPane {

//    public static boolean PLAYER_ROLE;//true if my role , false if computer role
    Cell PressedCell = null;

    WhitePawn whitepawn;
    Knight knight;
    Bishop bishop;
    Rook rook;
    Queen queen;
    King king;
    BlackPawn blackpawn;

    Stage popUpStage = null;

    public Board() {
        ref.cells = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        ref.cells[i][j] = new Cell((ChessGame.WIDTH / 8) - 1, (ChessGame.HEIGHT / 8) - 1, ref.cadeTblue, i, j);
                        add(ref.cells[i][j], j, i);
                    } else {
                        ref.cells[i][j] = new Cell((ChessGame.WIDTH / 8) - 1, (ChessGame.HEIGHT / 8) - 1, ref.antiqueWhite, i, j);
                        add(ref.cells[i][j], j, i);
                    }
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        ref.cells[i][j] = new Cell((ChessGame.WIDTH / 8) - 1, (ChessGame.HEIGHT / 8) - 1, ref.antiqueWhite, i, j);
                        add(ref.cells[i][j], j, i);
                    } else {
                        ref.cells[i][j] = new Cell((ChessGame.WIDTH / 8) - 1, (ChessGame.HEIGHT / 8) - 1, ref.cadeTblue, i, j);
                        add(ref.cells[i][j], j, i);
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int x = i;
                int y = j;
                ref.cells[i][j].setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        if (popUpStage == null) {
                            popUpStage = new Stage();
                            popUpStage.initStyle(StageStyle.TRANSPARENT);

                            Button close = new Button("Close");
                            Button minimize = new Button("Minimize");
                            close.setPrefSize(100, 25);
                            close.setOnAction(ex -> {
                                System.out.println("system exits!");
                                popUpStage.close();
                                popUpStage = null;
                                System.exit(0);
                            });

                            minimize.setPrefSize(100, 25);
                            minimize.setOnAction(ex -> {
                                ChessGame.primaryStage.setIconified(true);
                                popUpStage.close();
                                popUpStage = null;
                            });

                            close.setId("menuitem");
                            minimize.setId("menuitem");

                            VBox pane = new VBox();
                            pane.getChildren().addAll(close, minimize);
                            Scene scene = new Scene(pane, 100, 50);
                            popUpStage.setScene(scene);
                            scene.setFill(Color.TRANSPARENT);
                            popUpStage.setX(e.getScreenX());
                            popUpStage.setY(e.getScreenY());
                            popUpStage.show();
                        } else if (popUpStage != null) {

                            popUpStage.close();
                            popUpStage = null;
                        }
                    } else {
                        if (popUpStage != null) {
                            popUpStage.close();
                            popUpStage = null;
                        }
                        mouseClicked(x, y);
                    }
                });
            }
        }
        setImages();

    }

    public void mouseClicked(int x, int y) {
        System.out.println("(i , j) ==> " + x + " , " + y);
        whitepawn = new WhitePawn();
        knight = new Knight();
        bishop = new Bishop();
        rook = new Rook();
        queen = new Queen();
        king = new King();
        blackpawn = new BlackPawn();
//        if(!PLAYER_ROLE){
//            Checker c = new Checker();
//        }else{
        //if cell has no image & has no action holder above it
        if (cells[x][y].getImage() == null && (cells[x][y].getFill() == cadeTblue || cells[x][y].getFill() == antiqueWhite)) {
            //do nothing with it for now!
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
        }//if cell threatened dynamically "only with pawn"
        else if (cells[x][y].getFill() == dynamic) {
            //if dynamic cell on enemy piece left
            if (y - 1 >= 0) {
                cells[x][y-1].setNullImage();                     //throw away it's image
                cells[x][y-1].getChildren().remove(1);            //remove image object from the cell pane
                cells[x][y].setImage(PressedCell.getImage());   //set the killer image on the cell pane

                PressedCell.setNullImage();                                           //throw away the image
                cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);//remove image object from the cell pane

                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
            }
            //if dynamic cell on enemy piece right
            if(y + 1 <= 7){
                cells[x][y+1].setNullImage();                     //throw away it's image
                cells[x][y+1].getChildren().remove(1);            //remove image object from the cell pane
                cells[x][y].setImage(PressedCell.getImage());   //set the killer image on the cell pane

                PressedCell.setNullImage();                                           //throw away the image
                cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);//remove image object from the cell pane

                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
            }
        } //if cell threatened to be killed
        else if (cells[x][y].getFill() == killing) {

            //if piece is threatened by another one
            cells[x][y].setNullImage();                     //throw away it's image
            cells[x][y].getChildren().remove(1);            //remove image object from the cell pane
            cells[x][y].setImage(PressedCell.getImage());   //set the killer image on the cell pane

            PressedCell.setNullImage();                                           //throw away the image
            cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);//remove image object from the cell pane

            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
        } //if cell is hovered & available for movement
        else if (cells[x][y].getFill() == hover && cells[x][y].getImage() == null) {
            //if cell is hovered for movement
            cells[x][y].setImage(PressedCell.getImage());
            PressedCell.setNullImage();
            cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
        } //if white side chatacter is pressed
        else {
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
            cells[x][y].setBackgroundColor();
            //if the piece was a whitepawn
            if (cells[x][y].getImage().contains("wpawn")) {
                System.out.println("whitepawn");
                whitepawn.setWay(x, y);
            }//if the piece was a blackpawn
            else if (cells[x][y].getImage().contains("kpawn")) {
                System.out.println("blackpawn");
                blackpawn.setWay(x, y);
            }//if the piece was whiterook
            else if (cells[x][y].getImage().contains("rook")) {
                System.out.println("rook");
                rook.setWay(x, y);
            }//if the piece was whiteknight
            else if (cells[x][y].getImage().contains("knight")) {
                System.out.println("knight");
                knight.setWay(x, y);
            }//if the piece was whitebishop
            else if (cells[x][y].getImage().contains("bishop")) {
                System.out.println("bishop");
                bishop.setWay(x, y);
            }//if the piece was whitequeen
            else if (cells[x][y].getImage().contains("queen")) {
                System.out.println("queen");
                queen.setWay(x, y);
            }//if the piece was whiteking
            else if (cells[x][y].getImage().contains("king")) {
                System.out.println("king");
                king.setWay(x, y);
            }
            PressedCell = cells[x][y];
        }
//        }
    }

    public void setImages() {
        //set black images on fields
        cells[0][0].setImage("blackrook");
        cells[0][7].setImage("blackrook");

        cells[0][1].setImage("blackknight");
        cells[0][6].setImage("blackknight");

        cells[0][2].setImage("blackbishop");
        cells[0][5].setImage("blackbishop");

        cells[0][3].setImage("blackking");
        cells[0][4].setImage("blackqueen");

        for (int i = 0; i < 8; i++) {
            cells[1][i].setImage("blackpawn");
        }

        //set yellow images on fields
        cells[7][0].setImage("yellowrook");
        cells[7][7].setImage("yellowrook");

        cells[7][1].setImage("yellowknight");
        cells[7][6].setImage("yellowknight");

        cells[7][2].setImage("yellowbishop");
        cells[7][5].setImage("yellowbishop");

        cells[7][3].setImage("yellowking");
        cells[7][4].setImage("yellowqueen");

        for (int i = 0; i < 8; i++) {
            cells[6][i].setImage("yellowpawn");
        }
    }

    public void setCellsBackgroundDefaults() {
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        cells[i][j].setFill(cadeTblue);
                    } else {
                        cells[i][j].setFill(antiqueWhite);
                    }
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        cells[i][j].setFill(antiqueWhite);
                    } else {
                        cells[i][j].setFill(cadeTblue);
                    }
                }
            }
        }
    }

}
