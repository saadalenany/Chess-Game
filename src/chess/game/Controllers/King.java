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

    //arrayList of available moves for King
    ArrayList<String> Kingavailable = new ArrayList<>();

    //note that the String size will always be 3 if the place in King's range & no one else's ,format like this "1,2" means piece is hovered
    //in special case if the place in king's range is also in the range of any whiteside piece the String size will be 4 ,format like this "1,2="

    @Override
    public void setWay(int x, int y) {
        if (mode == 1) {
            //if user plays , consider his play just as in 2 players mode
            if (ref.cells[x][y].getImage().contains("yellow")) {
                moveOnMode2(x, y);
            } else {
                moveOnMode1(x, y);
            }
        } else {
            moveOnMode2(x, y);
        }
    }

    //code for 1 player mode
    public void moveOnMode1(int x, int y) {

    }

    //code for 2 player mode
    public void moveOnMode2(int x, int y) {
        new Rook().setWay(x, y);
        new Bishop().setWay(x, y);
    }

}
