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
public class King implements Piece {

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
        new Rook().setWay(x, y);
        new Bishop().setWay(x, y);
    }

}
