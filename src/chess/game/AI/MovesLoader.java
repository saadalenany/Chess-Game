/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.AI;

import chess.game.ref;
import static chess.game.ref.cells;
import java.util.ArrayList;

/**
 *
 * @author Saad
 */
public class MovesLoader {

    //arrayList of available moves for King
    public static ArrayList<Custom> Kingavailable = new ArrayList<>();

    //arrayList of available moves for Bishop
    public static ArrayList<Custom>[] Bishopavailable = new ArrayList[2];

    //arrayList of available moves for Knight
    public static ArrayList<Custom>[] Knightavailable = new ArrayList[2];

    //arrayList of available moves for Rook
    public static ArrayList<Custom>[] Rookavailable = new ArrayList[2];

    //arrayList of available moves for Queen
    public static ArrayList<Custom> Queenavailable = new ArrayList<>();

    //array of arrayLists of available moves for every Black Pawn
    public static ArrayList<Custom>[] Pawnavailable = new ArrayList[8];

    //arrayList of available moves of the other side
    static ArrayList<Custom> otherSide = new ArrayList<>();

    //load all available moves for all pieces
    public static void loadAllAvailableMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if (cells[i][j].getImage().contains("black")) {
                        //if the found piece was a bishop
                        if (cells[i][j].getImage().contains("bishop")) {
                            for (int z = 0; z < Bishopavailable.length; z++) {
                                if (cells[i][j].getNumber() == (z + 9)) {
                                    Bishopavailable[z] = getBishopRange(i, j);
                                }
                            }
                            //if the found piece was a king
                        } else if (cells[i][j].getImage().contains("king")) {
                            Kingavailable = getKingRange(i, j);
                            //if the found piece was a queen
                        } else if (cells[i][j].getImage().contains("queen")) {
                            Queenavailable = getQueenRange(i, j);
                            //if the found piece was a rook
                        } else if (cells[i][j].getImage().contains("rook")) {
                            for (int z = 0; z < Rookavailable.length; z++) {
                                if (cells[i][j].getNumber() == (z + 11)) {
                                    Rookavailable[z] = getRookRange(i, j);
                               }
                            }
                            //if the found piece was a knight
                        } else if (cells[i][j].getImage().contains("knight")) {
                            for (int z = 0; z < Knightavailable.length; z++) {
                                if (cells[i][j].getNumber() == (z + 13)) {
                                    Knightavailable[z] = getKnightRange(i, j);
                                }
                            }
                            //if the found piece was a pawn
                        } else if (cells[i][j].getImage().contains("pawn")) {
                            for (int z = 0; z < Pawnavailable.length; z++) {
                                if (cells[i][j].getNumber() == (z + 1)) {
                                    Pawnavailable[z] = getBlackPawnRange(i, j);
                                }
                            }
                        }
                    } else {
                        otherSide.clear();
                        //if the found piece was a bishop
                        if (cells[i][j].getImage().contains("bishop")) {
                            ArrayList<Custom> c = getBishopRange(i, j);
                            for (int x = 0; x < c.size(); x++) {
                                otherSide.add(c.get(x));
                            }
                            //if the found piece was a king
                        } else if (cells[i][j].getImage().contains("king")) {
                            ArrayList<Custom> c = getKingRange(i, j);
                            for (int x = 0; x < c.size(); x++) {
                                otherSide.add(c.get(x));
                            }
                            //if the found piece was a queen
                        } else if (cells[i][j].getImage().contains("queen")) {
                            ArrayList<Custom> c = getQueenRange(i, j);
                            for (int x = 0; x < c.size(); x++) {
                                otherSide.add(c.get(x));
                            }
                            //if the found piece was a rook
                        } else if (cells[i][j].getImage().contains("rook")) {
                            ArrayList<Custom> c = getRookRange(i, j);
                            for (int x = 0; x < c.size(); x++) {
                                otherSide.add(c.get(x));
                            }
                            //if the found piece was a knight
                        } else if (cells[i][j].getImage().contains("knight")) {
                            ArrayList<Custom> c = getKnightRange(i, j);
                            for (int x = 0; x < c.size(); x++) {
                                otherSide.add(c.get(x));
                            }
                            //if the found piece was a pawn
                        } else if (cells[i][j].getImage().contains("pawn")) {
                            for (int z = 0; z < 8; z++) {
                                if (cells[i][j].getNumber() == (z + 1)) {
                                    ArrayList<Custom> c = getWhitePawnRange(i, j);
                                    for (int x = 0; x < c.size(); x++) {
                                        otherSide.add(c.get(x));
                                    }
                                }
                            }
                        }
                    }
                } catch (NullPointerException e) {
                }
            }
        }
        for(int i=0 ; i<otherSide.size() ; i++){
            if(otherSide.get(i).getValue() == 'h'){
                cells[otherSide.get(i).getX()][otherSide.get(i).getY()].setBcase('h');
            }else if(otherSide.get(i).getValue() == 'k'){
                cells[otherSide.get(i).getX()][otherSide.get(i).getY()].setBcase('k');
            }
        }
    }

    public static ArrayList<Custom> getBishopRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        if (ref.cells[x][y].getImage().contains("black")) {
            checkDirectionsForBishop(x, y, "yellow", arr);
            return arr;
        } else {
            checkDirectionsForBishop(x, y, "black", arr);
            return arr;
        }
    }

    public static ArrayList<Custom> getRookRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        if (ref.cells[x][y].getImage().contains("black")) {
            checkDirectionsForRook(x, y, "yellow", arr);
            return arr;
        } else {
            checkDirectionsForRook(x, y, "black", arr);
            return arr;
        }
    }

    public static ArrayList<Custom> getKingRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        if (ref.cells[x][y].getImage().contains("black")) {
            checkDirectionsForBishop(x, y, "yellow", arr);
            checkDirectionsForRook(x, y, "yellow", arr);

            return arr;
        } else {
            checkDirectionsForBishop(x, y, "black", arr);
            checkDirectionsForRook(x, y, "black", arr);

            return arr;
        }
    }

    public static ArrayList<Custom> getKnightRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        if (ref.cells[x][y].getImage().contains("black")) {
            if (x - 2 >= 0 && x - 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 2, y + 1, "yellow", arr,x,y);
            }

            if (x - 1 >= 0 && x - 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y + 2, "yellow", arr,x,y);
            }

            if (x - 2 >= 0 && x - 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 2, y - 1, "yellow", arr,x,y);
            }

            if (x - 1 >= 0 && x - 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y - 2, "yellow", arr,x,y);
            }

            if (x + 2 >= 0 && x + 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 2, y + 1, "yellow", arr,x,y);
            }

            if (x + 1 >= 0 && x + 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y + 2, "yellow", arr,x,y);
            }

            if (x + 2 >= 0 && x + 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 2, y - 1, "yellow", arr,x,y);
            }

            if (x + 1 >= 0 && x + 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y - 2, "yellow", arr,x,y);
            }

            return arr;
        } else {
            if (x - 2 >= 0 && x - 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 2, y + 1, "black", arr,x,y);
            }

            if (x - 1 >= 0 && x - 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y + 2, "black", arr,x,y);
            }

            if (x - 2 >= 0 && x - 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 2, y - 1, "black", arr,x,y);
            }

            if (x - 1 >= 0 && x - 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y - 2, "black", arr,x,y);
            }

            if (x + 2 >= 0 && x + 2 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 2, y + 1, "black", arr,x,y);
            }

            if (x + 1 >= 0 && x + 1 <= 7 && y + 2 >= 0 && y + 2 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y + 2, "black", arr,x,y);
            }

            if (x + 2 >= 0 && x + 2 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 2, y - 1, "black", arr,x,y);
            }

            if (x + 1 >= 0 && x + 1 <= 7 && y - 2 >= 0 && y - 2 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y - 2, "black", arr,x,y);
            }
            return arr;
        }
    }

    public static ArrayList<Custom> getQueenRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        if (ref.cells[x][y].getImage().contains("black")) {
            //top left
            if (x - 1 >= 0 && x - 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y - 1, "yellow", arr,x,y);
            }

            //top center
            if (x - 1 >= 0 && x - 1 <= 7 && y >= 0 && y <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y, "yellow", arr,x,y);
            }

            //top right
            if (x - 1 >= 0 && x - 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y + 1, "yellow", arr,x,y);
            }

            //on left
            if (x >= 0 && x <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x, y - 1, "yellow", arr,x,y);
            }

            //on right
            if (x >= 0 && x <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x, y + 1, "yellow", arr,x,y);
            }

            //down left
            if (x + 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y - 1, "yellow", arr,x,y);
            }

            //down center
            if (x + 1 >= 0 && x + 1 <= 7 && y >= 0 && y <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y, "yellow", arr,x,y);
            }

            //down right
            if (x + 1 >= 0 && x + 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y + 1, "yellow", arr,x,y);
            }
            return arr;
        } else {
            //top left
            if (x - 1 >= 0 && x - 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y - 1, "black", arr,x,y);
            }

            //top center
            if (x - 1 >= 0 && x - 1 <= 7 && y >= 0 && y <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y, "black", arr,x,y);
            }

            //top right
            if (x - 1 >= 0 && x - 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x - 1, y + 1, "black", arr,x,y);
            }

            //on left
            if (x >= 0 && x <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x, y - 1, "black", arr,x,y);
            }

            //on right
            if (x >= 0 && x <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x, y + 1, "black", arr,x,y);
            }

            //down left
            if (x + 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y - 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y - 1, "black", arr,x,y);
            }

            //down center
            if (x + 1 >= 0 && x + 1 <= 7 && y >= 0 && y <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y, "black", arr,x,y);
            }

            //down right
            if (x + 1 >= 0 && x + 1 <= 7 && y + 1 >= 0 && y + 1 <= 7) {
                checkDirectionsForKnightAndQueen(x + 1, y + 1, "black", arr,x,y);
            }
            return arr;
        }

    }

    public static ArrayList<Custom> getBlackPawnRange(int x, int y) {
        ArrayList<Custom> arr = new ArrayList<>();
        //if cell on row 1
        if (x == 1) {
            if (ref.cells[x + 1][y].getImage() == null) {
                arr.add(new Custom(x + 1, y, 'h',x,y));
                if (ref.cells[x + 2][y].getImage() == null) {
                    arr.add(new Custom(x + 2, y, 'h',x,y));
                } else {
                    System.out.println("There is an image here!");
                }
            } else {
                System.out.println("There is an image here!");
            }

            if (x - 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y + 1 <= 7) {
                if (ref.cells[x + 1][y - 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y - 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    arr.add(new Custom(x + 1, y - 1, 'k',x,y));
                }

                if (ref.cells[x + 1][y + 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y + 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    arr.add(new Custom(x + 1, y + 1, 'k',x,y));
                }
            }

        } else if (x + 1 <= 7 && x - 1 >= 0) {
            if (ref.cells[x + 1][y].getImage() == null) {
                arr.add(new Custom(x + 1, y, 'h',x,y));
            } else {
                System.out.println("There is an image here!");
            }

            if (y - 1 >= 0 && y + 1 <= 7) {
                if (ref.cells[x + 1][y - 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y - 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    arr.add(new Custom(x + 1, y - 1, 'k',x,y));
                }

                if (ref.cells[x + 1][y + 1].getImage() == null) {
                    System.out.println("yellow not here!");
                } else if (ref.cells[x + 1][y + 1].getImage().contains("yellow")) {
                    System.out.println("yellow here");
                    arr.add(new Custom(x + 1, y + 1, 'k',x,y));
                }
            }
        }
        return arr;
    }

    public static ArrayList<Custom> getWhitePawnRange(int x, int y){
        ArrayList<Custom> arr = new ArrayList();
        //if cell on row 6
        if (x == 6) {
            if (ref.cells[x - 1][y].getImage() == null) {
                arr.add(new Custom(x - 1, y, 'h',x,y));
                if (ref.cells[x - 2][y].getImage() == null) {
                    arr.add(new Custom(x - 2, y, 'h',x,y));
                } else {
                    System.out.println("There is an image here!");
                }
            } else {
                System.out.println("There is an image here!");
            }

            if (x - 1 >= 0 && x + 1 <= 7 && y - 1 >= 0 && y + 1 <= 7) {
                if(ref.cells[x - 1][y - 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y - 1].getImage().contains("black")) {
                    System.out.println("black here");
                    arr.add(new Custom(x - 1, y - 1, 'k',x,y));
                }

                if(ref.cells[x - 1][y + 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y + 1].getImage().contains("black")) {
                    System.out.println("black here");
                    arr.add(new Custom(x - 1, y + 1, 'k',x,y));
                }
            }

        } else if (x + 1 <= 7 && x - 1 >= 0) {
            if (ref.cells[x - 1][y].getImage() == null) {
                arr.add(new Custom(x - 1, y, 'h',x,y));
            } else {
                System.out.println("There is an image here!");
            }

            if (y - 1 >= 0 && y + 1 <= 7) {
                if(ref.cells[x - 1][y - 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y - 1].getImage().contains("black")) {
                    System.out.println("black here");
                    arr.add(new Custom(x - 1, y - 1, 'k',x,y));
                }

                if(ref.cells[x - 1][y + 1].getImage() == null){
                    System.out.println("black not here!");
                }else if (ref.cells[x - 1][y + 1].getImage().contains("black")) {
                    System.out.println("black here");
                    arr.add(new Custom(x - 1, y + 1, 'h',x,y));
                }
            }

        }
        return arr;
    }

    public static void checkDirectionsForRook(int x, int y, String imageTitle, ArrayList<Custom> arr) {
        if (x < 7) {
            for (int i = x + 1; i < 8; i++) {
                if (ref.cells[i][y].getImage() == null) {
                    arr.add(new Custom(i, y, 'h',x,y));
                } else if (ref.cells[i][y].getImage().contains(imageTitle)) {
                    arr.add(new Custom(i, y, 'k',x,y));
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
        if (x > 0) {
            for (int i = x - 1; i >= 0; i--) {
                if (ref.cells[i][y].getImage() == null) {
                    arr.add(new Custom(i, y, 'h',x,y));
                } else if (ref.cells[i][y].getImage().contains(imageTitle)) {
                    arr.add(new Custom(i, y, 'k',x,y));
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
        if (y < 7) {
            for (int i = y + 1; i < 8; i++) {
                if (ref.cells[x][i].getImage() == null) {
                    arr.add(new Custom(x, i, 'h',x,y));
                } else if (ref.cells[x][i].getImage().contains(imageTitle)) {
                    arr.add(new Custom(x, i, 'k',x,y));
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
        if (y > 0) {
            for (int i = y - 1; i >= 0; i--) {
                if (ref.cells[x][i].getImage() == null) {
                    arr.add(new Custom(x, i, 'h',x,y));
                } else if (ref.cells[x][i].getImage().contains(imageTitle)) {
                    arr.add(new Custom(x, i, 'k',x,y));
                    break;
                } else {
                    System.out.println("There is an image here!");
                    break;
                }
            }
        }
    }

    public static void checkDirectionsForBishop(int x, int y, String imageTitle, ArrayList<Custom> arr) {
        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (ref.cells[i][j].getImage() == null) {
                arr.add(new Custom(i, j, 'h',x,y));
            } else if (ref.cells[i][j].getImage().contains(imageTitle)) {
                arr.add(new Custom(i, j, 'k',x,y));
                break;
            } else {
                System.out.println("There is an image here!");
                break;
            }
        }
        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (ref.cells[i][j].getImage() == null) {
                arr.add(new Custom(i, j, 'h',x,y));
            } else if (ref.cells[i][j].getImage().contains(imageTitle)) {
                arr.add(new Custom(i, j, 'k',x,y));
                break;
            } else {
                System.out.println("There is an image here!");
                break;
            }
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (ref.cells[i][j].getImage() == null) {
                arr.add(new Custom(i, j, 'h',x,y));
            } else if (ref.cells[i][j].getImage().contains(imageTitle)) {
                arr.add(new Custom(i, j, 'k',x,y));
                break;
            } else {
                System.out.println("There is an image here!");
                break;
            }
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (ref.cells[i][j].getImage() == null) {
                arr.add(new Custom(i, j, 'h',x,y));
            } else if (ref.cells[i][j].getImage().contains(imageTitle)) {
                arr.add(new Custom(i, j, 'k',x,y));
                break;
            } else {
                System.out.println("There is an image here!");
                break;
            }
        }

    }

    public static void checkDirectionsForKnightAndQueen(int i, int j, String imageTitle, ArrayList<Custom> arr,int x , int y) {
        if (ref.cells[i][j].getImage() == null) {
            arr.add(new Custom(i, j, 'h',x,y));
        } else if (ref.cells[i][j].getImage().contains(imageTitle)) {
            arr.add(new Custom(i, j, 'k',x,y));
        } else {
            System.out.println("There is an image here!");
        }
    }

}
