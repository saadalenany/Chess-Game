/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.Controllers;

/**
 *
 * @author Saad
 */
public class King implements Piece{

    @Override
    public void setWay(int x ,int y) {
        new Rook().setWay(x, y);
        new Bishop().setWay(x, y);
    }

}
