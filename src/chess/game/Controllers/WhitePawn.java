/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.Controllers;

import chess.game.ref;

/**
 *
 * @author Saad
 */
public class WhitePawn implements Piece {

    @Override
    public void setWay(int x, int y) {

        //if cell on row 6
        if (x == 6) {
            if (ref.cells[x - 1][y].getImage() == null) {
                ref.cells[x - 1][y].setFill(ref.hover);
                if (ref.cells[x - 2][y].getImage() == null) {
                    ref.cells[x - 2][y].setFill(ref.hover);
                } else {
                    System.out.println("There is an image here!");
                }
            } else {
                System.out.println("There is an image here!");
            }

            if (y - 1 >= 0 && y + 1 <= 7) {
                if(ref.cells[x - 1][y - 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y - 1].getImage().contains("black")) {
                    System.out.println("black here");
                    ref.cells[x - 1][y - 1].setFill(ref.killing);
                }

                if(ref.cells[x - 1][y + 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y + 1].getImage().contains("black")) {
                    System.out.println("black here");
                    ref.cells[x - 1][y + 1].setFill(ref.killing);
                }
            }

        } else if (x < 6 && x > 0) {
            if (ref.cells[x - 1][y].getImage() == null) {
                ref.cells[x - 1][y].setFill(ref.hover);
            } else {
                System.out.println("There is an image here!");
            }

            if (y - 1 >= 0 && y + 1 <= 7) {
                if(ref.cells[x - 1][y - 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y - 1].getImage().contains("black")) {
                    System.out.println("black here");
                    ref.cells[x - 1][y - 1].setFill(ref.killing);
                }

                if(ref.cells[x - 1][y + 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y + 1].getImage().contains("black")) {
                    System.out.println("black here");
                    ref.cells[x - 1][y + 1].setFill(ref.killing);
                }
            }

        }

    }

}
