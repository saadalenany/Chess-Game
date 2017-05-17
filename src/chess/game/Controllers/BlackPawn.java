/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.Controllers;

import static chess.game.Modes.mode;
import chess.game.ref;
import java.util.ArrayList;

/**
 *
 * @author Saad
 */
public class BlackPawn implements Piece {

    //note that the String size will always be 3 if the place in Black Pawn's range & no one else's ,format like this "1,2" means piece is hovered
    //in special case if the place in Black Pawn's range is also in the range of any whiteside piece the String size will be 4 ,format like this "1,2="

    @Override
    public void setWay(int x, int y) {
        moveOnMode2(x, y);
    }

    //code for 2 players
    public void moveOnMode2(int x, int y) {
        //if cell on row 1
        if (x == 1) {
            if (ref.cells[x + 1][y].getImage() == null) {
                ref.cells[x + 1][y].setFill(ref.hover);
                if (ref.cells[x + 2][y].getImage() == null) {
                    ref.cells[x + 2][y].setFill(ref.hover);
                } else {
                    System.out.println("There is an image here!");
                }
            } else {
                System.out.println("There is an image here!");
            }

            if (x - 1 >= 0 && x + 1 <= 7 && y-1 >= 0 && y+1 <= 7 ) {
                if (ref.cells[x + 1][y - 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y - 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    ref.cells[x + 1][y - 1].setFill(ref.killing);
                }

                if (ref.cells[x + 1][y + 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y + 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    ref.cells[x + 1][y + 1].setFill(ref.killing);
                }
            }

        } else if (x + 1 <= 7 && x - 1 >= 0) {
            if (ref.cells[x + 1][y].getImage() == null) {
                ref.cells[x + 1][y].setFill(ref.hover);
            } else {
                System.out.println("There is an image here!");
            }

            if (y - 1 >= 0 && y + 1 <= 7) {
                if (ref.cells[x + 1][y - 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y - 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    ref.cells[x + 1][y - 1].setFill(ref.killing);
                }

                if (ref.cells[x + 1][y + 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y + 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    ref.cells[x + 1][y + 1].setFill(ref.killing);
                }
            }
        }

    }

}
