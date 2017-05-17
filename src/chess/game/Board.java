/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import chess.game.AI.Checker;
import static chess.game.AI.MovesLoader.*;
import chess.game.Controllers.Bishop;
import chess.game.Controllers.BlackPawn;
import chess.game.Controllers.King;
import chess.game.Controllers.Knight;
import chess.game.Controllers.Queen;
import chess.game.Controllers.Rook;
import chess.game.Controllers.WhitePawn;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static chess.game.ref.*;
import static chess.game.Modes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

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

    int TWOMODEPLAYER = 0;      //0 ==> player 1 , 1 ==> player 2

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
                cells[i][j].setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.SECONDARY) {
                        showPopUp(e);
                    } else {
                        if (popUpStage != null) {
                            popUpStage.close();
                            popUpStage = null;
                        }
                        if (mode == 1) {
                            mouseClickedOnMode1(x, y);
                        } else {
                            mouseClickedOnMode2(x, y);
                        }
                    }
                });
            }
        }
        setImages();

    }

    public void mouseClickedOnMode1(int x, int y) {
        whitepawn = new WhitePawn();
        knight = new Knight();
        bishop = new Bishop();
        rook = new Rook();
        queen = new Queen();
        king = new King();
        blackpawn = new BlackPawn();

        //if cell has no image & has no action holder above it
        if (cells[x][y].getImage() == null && (cells[x][y].getFill() == cadeTblue || cells[x][y].getFill() == antiqueWhite)) {
            //do nothing with it for now!
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
        } //if cell threatened to be killed
        else if (cells[x][y].getFill() == killing) {

            if(cells[x][y].getImage().contains("kqueen")){
                Messager messager = new Messager("You win!");
            }else if (cells[x][y].getImage().contains("wqueen")){
                Messager messager = new Messager("You lose!");
            }else{
                //if piece is threatened by another one
                cells[x][y].setNonCharacter();                     //throw away it's image
                cells[x][y].getChildren().remove(1);            //remove image object from the cell pane
                cells[x][y].setImage(PressedCell.getImage());   //set the killer image on the cell pane

                if(cells[x][y].getImage().contains("pawn")){
                    NUMBER_OF_PAWNS--;
                }else if(cells[x][y].getImage().contains("bishop")){
                    NUMBER_OF_BISHOPS--;
                }else if(cells[x][y].getImage().contains("rook")){
                    NUMBER_OF_ROOKS--;
                }else if(cells[x][y].getImage().contains("knight")){
                    NUMBER_OF_KNIGHTS--;
                }

                PressedCell.setNonCharacter();                                        //throw away the image
                cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);//remove image object from the cell pane

                //do this to all pieces cells with images
                setCellsBackgroundDefaults();

                Checker checker = new Checker();
//
//                new Timeline().getKeyFrames().add(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
                        System.err.println("AI turn");
                        checker.movePiece();
//                    }
//                }));

            }

        } //if cell is hovered & available for movement
        else if (cells[x][y].getFill() == hover && cells[x][y].getImage() == null) {
            //if cell is hovered for movement
            cells[x][y].setImage(PressedCell.getImage());
            PressedCell.setNonCharacter();
            cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();

            Checker checker = new Checker();

//            new Timeline().getKeyFrames().add(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
                    System.err.println("AI turn");
                    checker.movePiece();
//                }
//            }));
        } //if white side chatacter is pressed
        else {
            //here user can only press on white pieces to move them
            //if the piece was a whitepawn
            if (cells[x][y].getImage().contains("wpawn")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whitepawn");
                whitepawn.setWay(x, y);
            } //if the piece was whiterook
            else if (cells[x][y].getImage().contains("wrook")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whiterook");
                rook.setWay(x, y);
            } //if the piece was whiteknight
            else if (cells[x][y].getImage().contains("wknight")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whiteknight");
                knight.setWay(x, y);
            } //if the piece was whitebishop
            else if (cells[x][y].getImage().contains("wbishop")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whitebishop");
                bishop.setWay(x, y);
            } //if the piece was whitequeen
            else if (cells[x][y].getImage().contains("wqueen")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whitequeen");
                queen.setWay(x, y);
            } //if the piece was whiteking
            else if (cells[x][y].getImage().contains("wking")) {
                //do this to all pieces cells with images
                setCellsBackgroundDefaults();
                cells[x][y].setBackgroundColor();
                System.out.println("whiteking");
                king.setWay(x, y);
            }
            PressedCell = cells[x][y];
        }

        //AI movement right after the user plays
        //here U can use or completely manipulate Checker & OtherSide classes & access the pieces classes from here
        //the pieces classes are complete in 2 players mode
        //but not yet in 1 player mode
        //so this is your task 
        //the classes u'll be working in
        //In Package Controllers
        //work on method moveOnMode1 in all pieces classes
        //also in package AI u are allowed to do as you will
        //& alse don't forget to put the AI portion here in the Board class
        //good luck ;)

    }

    public void mouseClickedOnMode2(int x, int y) {
        System.out.println("(i , j) ==> " + x + " , " + y);
        whitepawn = new WhitePawn();
        knight = new Knight();
        bishop = new Bishop();
        rook = new Rook();
        queen = new Queen();
        king = new King();
        blackpawn = new BlackPawn();

        //if cell has no image & has no action holder above it
        if (cells[x][y].getImage() == null && (cells[x][y].getFill() == cadeTblue || cells[x][y].getFill() == antiqueWhite)) {
            //do nothing with it for now!
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
        } //if cell threatened to be killed
        else if (cells[x][y].getFill() == killing) {

            if(cells[x][y].getImage().contains("kqueen")){
                Messager messager = new Messager("White Player wins!");
            }else if (cells[x][y].getImage().contains("wqueen")){
                Messager messager = new Messager("Black Player wins!");
            }else{
                //if piece is threatened by another one
                cells[x][y].setNonCharacter();                     //throw away it's image
                cells[x][y].getChildren().remove(1);            //remove image object from the cell pane
                cells[x][y].setNumber(PressedCell.getNumber());
                cells[x][y].setImage(PressedCell.getImage());   //set the killer image on the cell pane

                PressedCell.setNonCharacter();                                        //throw away the image
                cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);//remove image object from the cell pane

                //do this to all pieces cells with images
                setCellsBackgroundDefaults();

                TWOMODEPLAYER = 1 - TWOMODEPLAYER;

            }

        } //if cell is hovered & available for movement
        else if (cells[x][y].getFill() == hover && cells[x][y].getImage() == null) {
            //if cell is hovered for movement
            cells[x][y].setImage(PressedCell.getImage());
            cells[x][y].setNumber(PressedCell.getNumber());

            PressedCell.setNonCharacter();
            cells[PressedCell.getX()][PressedCell.getY()].getChildren().remove(1);

            //do this to all pieces cells with images
            setCellsBackgroundDefaults();

            TWOMODEPLAYER = 1 - TWOMODEPLAYER;

        } //if white side chatacter is pressed
        else {
            //do this to all pieces cells with images
            setCellsBackgroundDefaults();
            if(TWOMODEPLAYER == 0){
                //if the piece was a whitepawn
                if (cells[x][y].getImage().contains("wpawn")) {
                    cells[x][y].setBackgroundColor();
                    whitepawn.setWay(x, y);
                }
                //if the piece was whiterook
                else if (cells[x][y].getImage().contains("wrook")) {
                    cells[x][y].setBackgroundColor();
                    rook.setWay(x, y);
                }//if the piece was whiteknight
                else if (cells[x][y].getImage().contains("wknight")) {
                    cells[x][y].setBackgroundColor();
                    knight.setWay(x, y);
                }//if the piece was whitebishop
                else if (cells[x][y].getImage().contains("wbishop")) {
                    cells[x][y].setBackgroundColor();
                    bishop.setWay(x, y);
                }//if the piece was whitequeen
                else if (cells[x][y].getImage().contains("wqueen")) {
                    cells[x][y].setBackgroundColor();
                    queen.setWay(x, y);
                }//if the piece was whiteking
                else if (cells[x][y].getImage().contains("wking")) {
                    cells[x][y].setBackgroundColor();
                    king.setWay(x, y);
                }
            }else if(TWOMODEPLAYER == 1){
                cells[x][y].setBackgroundColor();
                //if the piece was a blackpawn
                if (cells[x][y].getImage().contains("kpawn")) {
                    cells[x][y].setBackgroundColor();
                    blackpawn.setWay(x, y);
                }
                //if the piece was whiterook
                else if (cells[x][y].getImage().contains("krook")) {
                    cells[x][y].setBackgroundColor();
                    rook.setWay(x, y);
                }//if the piece was whiteknight
                else if (cells[x][y].getImage().contains("kknight")) {
                    cells[x][y].setBackgroundColor();
                    knight.setWay(x, y);
                }//if the piece was whitebishop
                else if (cells[x][y].getImage().contains("kbishop")) {
                    cells[x][y].setBackgroundColor();
                    bishop.setWay(x, y);
                }//if the piece was whitequeen
                else if (cells[x][y].getImage().contains("kqueen")) {
                    cells[x][y].setBackgroundColor();
                    queen.setWay(x, y);
                }//if the piece was whiteking
                else if (cells[x][y].getImage().contains("kking")) {
                    cells[x][y].setBackgroundColor();
                    king.setWay(x, y);
                }
            }
            PressedCell = cells[x][y];
        }
    }

    public void setImages() {
        //set black images on fields
        cells[0][0].setImage("blackrook");
        cells[0][0].setNumber(11);
        cells[0][7].setImage("blackrook");
        cells[0][7].setNumber(12);

        cells[0][1].setImage("blackknight");
        cells[0][1].setNumber(13);
        cells[0][6].setImage("blackknight");
        cells[0][6].setNumber(14);

        cells[0][2].setImage("blackbishop");
        cells[0][2].setNumber(9);
        cells[0][5].setImage("blackbishop");
        cells[0][5].setNumber(10);

        cells[0][3].setImage("blackking");
        cells[0][3].setNumber(15);
        cells[0][4].setImage("blackqueen");
        cells[0][4].setNumber(16);

        for (int i = 0; i < 8; i++) {
            cells[1][i].setNumber(i+1);
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

    public void showPopUp(MouseEvent e) {
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
    }

}
