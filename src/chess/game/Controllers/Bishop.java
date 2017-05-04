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
public class Bishop implements Piece {

    int x, y;

    //arrayList of available moves for Bishop
    ArrayList<String> Bishopavailable = new ArrayList<>();

    //note that the String size will always be 3 if the place in Bishop's range & no one else's ,format like this "1,2" means piece is hovered
    //in special case if the place in Bishop's range is also in the range of any whiteside piece the String size will be 4 ,format like this "1,2="

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

    //code for 1 player
    public void moveOnMode1(int x, int y) {
        this.x = x;
        this.y = y;

    }

    //code for 2 players
    public void moveOnMode2(int x, int y) {
        this.x = x;
        this.y = y;

        //if piece inside boundries
        if (x < 7 && x > 0 && y < 7 && y > 0) {
            //hover direction down right
            hoverDownRight();

            //hover direction down left
            hoverDownLeft();

            //hover direction up left
            hoverUpLeft();

            //hover direction up right
            hoverUpRight();
        }

        //if piece is at the bottom but not at the cornors
        if (x == 7 && y > 0 && y < 7) {
            //hover direction up left
            hoverUpLeft();

            //hover direction up right
            hoverUpRight();
        }

        //if piece is at the top but not at the cornors
        if (x == 0 && y > 0 && y < 7) {
            //hover direction down right
            hoverDownRight();

            //hover direction down left
            hoverDownLeft();
        }

        //if piece is at the left but not at the corners
        if (y == 0 && x > 0 && x < 7) {
            //hover direction down right
            hoverDownRight();

            //hover direction up right
            hoverUpRight();
        }

        //if piece is at the right but not at the cornors
        if (y == 7 && x > 0 && x < 7) {
            //hover direction down left
            hoverDownLeft();

            //hover direction up left
            hoverUpLeft();
        }

        //handling the 4 sides of cornors
        //if piece is at the up left cornor ==> hover down right only
        if (x == 0 && y == 0) {
            //hover direction down right
            hoverDownRight();
        }

        //if piece is at the up right cornor ==> hover down left only
        if (x == 0 && y == 7) {
            //hover direction down left
            hoverDownLeft();
        }

        //if piece is at the down right cornor ==> hover up left only
        if (x == 7 && y == 7) {
            //hover direction up left
            hoverUpLeft();
        }

        //if piece is at the down left cornor ==> hover up right only
        if (x == 7 && y == 0) {
            //hover direction up right
            hoverUpRight();
        }
    }

    public void hoverDownRight() {

        if (ref.cells[x][y].getImage().contains("yellow")) {
            for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("black")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        } else if (ref.cells[x][y].getImage().contains("black")) {
            for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("yellow")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
    }

    public void hoverDownLeft() {

        if (ref.cells[x][y].getImage().contains("yellow")) {
            for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("black")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        } else if (ref.cells[x][y].getImage().contains("black")) {
            for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("yellow")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
    }

    public void hoverUpLeft() {

        if (ref.cells[x][y].getImage().contains("yellow")) {
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("black")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        } else if (ref.cells[x][y].getImage().contains("black")) {
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("yellow")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }

    }

    public void hoverUpRight() {

        if (ref.cells[x][y].getImage().contains("yellow")) {
            for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("black")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        } else if (ref.cells[x][y].getImage().contains("black")) {
            for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
                if (ref.cells[i][j].getImage() == null) {
                    ref.cells[i][j].setFill(ref.hover);
                } else if (ref.cells[i][j].getImage().contains("yellow")) {
                    ref.cells[i][j].setFill(ref.killing);
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
    }

}
