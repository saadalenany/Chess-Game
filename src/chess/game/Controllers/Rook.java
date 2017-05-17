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
public class Rook implements Piece {

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
        if (x < 7) {
            if (ref.cells[x][y].getImage().contains("yellow")) {
                for (int i = x + 1; i < 8; i++) {
                    if (ref.cells[i][y].getImage() == null) {
                        ref.cells[i][y].setFill(ref.hover);
                    } else if (ref.cells[i][y].getImage().contains("black")) {
                        ref.cells[i][y].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            } else if (ref.cells[x][y].getImage().contains("black")) {
                for (int i = x + 1; i < 8; i++) {
                    if (ref.cells[i][y].getImage() == null) {
                        ref.cells[i][y].setFill(ref.hover);
                    } else if (ref.cells[i][y].getImage().contains("yellow")) {
                        ref.cells[i][y].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            }
        }

        if (x > 0) {
            if (ref.cells[x][y].getImage().contains("yellow")) {
                for (int i = x - 1; i >= 0; i--) {
                    if (ref.cells[i][y].getImage() == null) {
                        ref.cells[i][y].setFill(ref.hover);
                    } else if (ref.cells[i][y].getImage().contains("black")) {
                        ref.cells[i][y].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            } else if (ref.cells[x][y].getImage().contains("black")) {
                for (int i = x - 1; i >= 0; i--) {
                    if (ref.cells[i][y].getImage() == null) {
                        ref.cells[i][y].setFill(ref.hover);
                    } else if (ref.cells[i][y].getImage().contains("yellow")) {
                        ref.cells[i][y].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            }
        }

        if (y < 7) {
            if (ref.cells[x][y].getImage().contains("yellow")) {
                for (int i = y + 1; i < 8; i++) {
                    if (ref.cells[x][i].getImage() == null) {
                        ref.cells[x][i].setFill(ref.hover);
                    } else if (ref.cells[x][i].getImage().contains("black")) {
                        ref.cells[x][i].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            } else if (ref.cells[x][y].getImage().contains("black")) {
                for (int i = y + 1; i < 8; i++) {
                    if (ref.cells[x][i].getImage() == null) {
                        ref.cells[x][i].setFill(ref.hover);
                    } else if (ref.cells[x][i].getImage().contains("yellow")) {
                        ref.cells[x][i].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            }

        }
        if (y > 0) {

            if (ref.cells[x][y].getImage().contains("yellow")) {
                for (int i = y - 1; i >= 0; i--) {
                    if (ref.cells[x][i].getImage() == null) {
                        ref.cells[x][i].setFill(ref.hover);
                    } else if (ref.cells[x][i].getImage().contains("black")) {
                        ref.cells[x][i].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            } else if (ref.cells[x][y].getImage().contains("black")) {
                for (int i = y - 1; i >= 0; i--) {
                    if (ref.cells[x][i].getImage() == null) {
                        ref.cells[x][i].setFill(ref.hover);
                    } else if (ref.cells[x][i].getImage().contains("yellow")) {
                        ref.cells[x][i].setFill(ref.killing);
                        break;
                    } else {
                        System.out.println("There is an image here!");
                        break;
                    }
                }
            }
        }
    }

}
