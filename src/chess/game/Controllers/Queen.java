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
public class Queen implements Piece {

    @Override
    public void setWay(int x, int y) {

        if (mode == 1) {
            //if user plays , consider his play just as in 2 players mode
            if (ref.cells[x][y].getImage().contains("yellow")) {
                moveOnMode2(x, y);
            }
        } else {
            moveOnMode2(x, y);
        }
    }

    //code for 2 player mode
    public void moveOnMode2(int x, int y) {
        if (ref.cells[x][y].getImage().contains("yellow")) {
            //top left
            if (x - 1 >= 0 && x - 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverBlack(x - 1, y - 1);
            }

            //top center
            if (x - 1 >= 0 && x - 1 <= 7 && y >= 0 && y <= 7) {
                hoverBlack(x - 1, y);
            }

            //top right
            if (x - 1 >= 0 && x - 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverBlack(x - 1, y + 1);
            }

            //on left
            if (x >= 0 && x <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverBlack(x, y - 1);
            }

            //on right
            if (x >= 0 && x <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverBlack(x, y + 1);
            }

            //down left
            if (x + 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverBlack(x + 1, y - 1);
            }

            //down center
            if (x + 1 >= 0 && x + 1 <= 7 && y >= 0 && y <= 7) {
                hoverBlack(x + 1, y);
            }

            //down right
            if (x + 1 >= 0 && x + 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverBlack(x + 1, y + 1);
            }

        } else if (ref.cells[x][y].getImage().contains("black")) {
            //top left
            if (x - 1 >= 0 && x - 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverYellow(x - 1, y - 1);
            }

            //top center
            if (x - 1 >= 0 && x - 1 <= 7 && y >= 0 && y <= 7) {
                hoverYellow(x - 1, y);
            }

            //top right
            if (x - 1 >= 0 && x - 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverYellow(x - 1, y + 1);
            }

            //on left
            if (x >= 0 && x <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverYellow(x, y - 1);
            }

            //on right
            if (x >= 0 && x <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverYellow(x, y + 1);
            }

            //down left
            if (x + 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                hoverYellow(x + 1, y - 1);
            }

            //down center
            if (x + 1 >= 0 && x + 1 <= 7 && y >= 0 && y <= 7) {
                hoverYellow(x + 1, y);
            }

            //down right
            if (x + 1 >= 0 && x + 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                hoverYellow(x + 1, y + 1);
            }

        }

    }

    public void hoverBlack(int i, int j) {
        if (ref.cells[i][j].getImage() == null) {
            ref.cells[i][j].setFill(ref.hover);
        } else if (ref.cells[i][j].getImage().contains("black")) {
            ref.cells[i][j].setFill(ref.killing);
        } else {
            System.out.println("There is an image here!");
        }
    }

    public void hoverYellow(int i, int j) {
        if (ref.cells[i][j].getImage() == null) {
            ref.cells[i][j].setFill(ref.hover);
        } else if (ref.cells[i][j].getImage().contains("yellow")) {
            ref.cells[i][j].setFill(ref.killing);
        } else {
            System.out.println("There is an image here!");
        }
    }

}
