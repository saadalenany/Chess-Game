/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.AI;

import static chess.game.AI.MovesLoader.*;
import chess.game.Messager;
import static chess.game.ref.*;
import java.util.ArrayList;

/**
 *
 * @author Saad
 */
public class Checker {

    ArrayList<String> killers = new ArrayList();

    boolean moved = false;

    //move the piece
    public void movePiece() {

        loadAllAvailableMoves();

        for(int i=0 ; i<Kingavailable.size() ; i++){
            System.out.println("king availables ==> "+Kingavailable.get(i).getX()+" , "+Kingavailable.get(i).getY());
        }
        for(int i=0 ; i<Queenavailable.size() ; i++){
            System.out.println("king availables ==> "+Queenavailable.get(i).getX()+" , "+Queenavailable.get(i).getY());
        }

        printAvailables(Pawnavailable,"pawn");
        printAvailables(Knightavailable,"knight");
        printAvailables(Bishopavailable,"bishop");
        printAvailables(Rookavailable,"rook");

        //After all available moves were loaded

        //strategy play
        // for AI
        //1. to win
        //2. to pervent the other from winning by protecting the AI Pieces
        //3. to build up a way to win
        //4. to threaten the other side
        //5. to eliminate the threats
        //6. to move randomly

        //if there is some piece to be killed
        //priorities to kill
        //1. king               ==> 100
        //2. queen              ==> 90
        //3. rook               ==> 70
        //4. bishop             ==> 50
        //5. knight             ==> 30
        //6. pawn               ==> 10

        if(!moved){
            for(int i=0 ; i<Kingavailable.size() ; i++){
                if(Kingavailable.get(i).getValue() == 'k'){
                    System.err.println("King kills");
                    kill(Kingavailable.get(i).getSelfX(), Kingavailable.get(i).getSelfY(), Kingavailable.get(i).getX(), Kingavailable.get(i).getY());
                    moved = true;
                    break;
                }
            }
        }

        if(!moved){
            for(int i=0 ; i<Queenavailable.size() ; i++){
                if(Queenavailable.get(i).getValue() == 'k'){
                    System.err.println("Queen kills");
                    kill(Queenavailable.get(i).getSelfX(), Queenavailable.get(i).getSelfY(), Queenavailable.get(i).getX(), Queenavailable.get(i).getY());
                    moved = true;
                    break;
                }
            }
        }

        if(!moved){
            for(int xx = 0 ; xx< NUMBER_OF_ROOKS ; xx++){
                for(int i=0 ; i<Rookavailable[xx].size() ; i++){
                    if(Rookavailable[xx].get(i).getValue() == 'k'){
                        System.err.println("Rook kills");
                        kill(Rookavailable[xx].get(i).getSelfX(), Rookavailable[xx].get(i).getSelfY(), Rookavailable[xx].get(i).getX(), Rookavailable[xx].get(i).getY());
                        moved = true;
                        break;
                    }
                }
            }
        }

        if(!moved){
            for(int xx = 0 ; xx< NUMBER_OF_BISHOPS ; xx++){            
                for(int i=0 ; i<Bishopavailable[xx].size() ; i++){
                    if(Bishopavailable[xx].get(i).getValue() == 'k'){
                        System.err.println("Bishop kills");
                        kill(Bishopavailable[xx].get(i).getSelfX(), Bishopavailable[xx].get(i).getSelfY(), Bishopavailable[xx].get(i).getX(), Bishopavailable[xx].get(i).getY());
                        moved = true;
                        break;
                    }
                }
            }
        }

        if(!moved){
            for(int xx = 0 ; xx < NUMBER_OF_KNIGHTS ; xx++){
                for(int i=0 ; i<Knightavailable[xx].size() ; i++){
                    if(Knightavailable[xx].get(i).getValue() == 'k'){
                        System.err.println("KNIGHT kills");
                        kill(Knightavailable[xx].get(i).getSelfX(), Knightavailable[xx].get(i).getSelfY(), Knightavailable[xx].get(i).getX(), Knightavailable[xx].get(i).getY());
                        moved = true;
                        break;
                    }
                }
            }
        }

        if(!moved){
            for(int xx = 0 ; xx < NUMBER_OF_PAWNS ; xx++){
                for(int i=0 ; i<Pawnavailable[xx].size() ; i++){
                    if(Pawnavailable[xx].get(i).getValue() == 'k'){
                        System.err.println("pawn kills");
                        kill(Pawnavailable[xx].get(i).getSelfX(), Pawnavailable[xx].get(i).getSelfY(), Pawnavailable[xx].get(i).getX(), Pawnavailable[xx].get(i).getY());
                        moved = true;
                        break;
                    }
                }
            }
        }

        do{
            int rand = (int) (Math.random() * 6);

            // select a pawn
            switch (rand) {
                case 0:
                    if(Pawnavailable.length > 0){
                        int pawnrand = (int) (Math.random()*NUMBER_OF_PAWNS);
                        int[] c = getPiece(pawnrand+1 , "pawn");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }

                        int randmove = (int) (Math.random()*Pawnavailable[pawnrand].size());
                        if(Pawnavailable[pawnrand].size() > 0){
                            System.err.println("pawn pos "+pawnrand);
                            System.out.println("target x ==> "+Pawnavailable[pawnrand].get(randmove).getX()+" , y ==> "+Pawnavailable[pawnrand].get(randmove).getY());
                            System.out.println("pawn at x ==> "+c[0]+" , y ==> "+c[1]);

                            simpleMove(Pawnavailable[pawnrand].get(randmove).getX(), Pawnavailable[pawnrand].get(randmove).getY(), c[0] , c[1]);
                            moved = true;
                        }
                    }
                    break;
                    // select a knight
                case 1:
                    if(Knightavailable.length > 0){
                        int knightrand = (int) (Math.random()*NUMBER_OF_KNIGHTS);
                        int[] c = getPiece(knightrand+13 , "knight");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }

                        int randmove = (int) (Math.random()*Knightavailable[knightrand].size());
                        if(Knightavailable[knightrand].size() > 0){
                            System.err.println("knight pos "+knightrand);
                            System.out.println("target x ==> "+Knightavailable[knightrand].get(randmove).getX()+" , y ==> "+Knightavailable[knightrand].get(randmove).getY());
                            System.out.println("knight at x ==> "+c[0]+" , y ==> "+c[1]);

                            simpleMove(Knightavailable[knightrand].get(randmove).getX(), Knightavailable[knightrand].get(randmove).getY(), c[0] , c[1]);
                            moved = true;
                        }
                    }
                    break;
                    // select a bishop
                case 2:
                    if(Bishopavailable.length > 0){
                        int bishoprand = (int) (Math.random()*NUMBER_OF_BISHOPS);
                        int[] c = getPiece(bishoprand+9 , "bishop");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }

                        int randmove = (int) (Math.random()*Bishopavailable[bishoprand].size());
                        if(Bishopavailable[bishoprand].size() > 0){
                            System.err.println("bishop pos "+bishoprand);
                            System.out.println("target x ==> "+Bishopavailable[bishoprand].get(randmove).getX()+" , y ==> "+Bishopavailable[bishoprand].get(randmove).getY());
                            System.out.println("bishop at x ==> "+c[0]+" , y ==> "+c[1]);

                            simpleMove(Bishopavailable[bishoprand].get(randmove).getX(), Bishopavailable[bishoprand].get(randmove).getY(), c[0] , c[1]);
                            moved = true;
                        }
                    }
                    break;
                    // select a rook
                case 3:
                    if(Rookavailable.length > 0){
                        int rookrand = (int) (Math.random()*NUMBER_OF_ROOKS);
                        int[] c = getPiece(rookrand+11 , "rook");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }

                        int randmove = (int) (Math.random()*Rookavailable[rookrand].size());
                        if(Rookavailable[rookrand].size() > 0){
                            System.err.println("rook pos "+rookrand);
                            System.out.println("target x ==> "+Rookavailable[rookrand].get(randmove).getX()+" , y ==> "+Rookavailable[rookrand].get(randmove).getY());
                            System.out.println("rook at x ==> "+c[0]+" , y ==> "+c[1]);
                            simpleMove(Rookavailable[rookrand].get(randmove).getX(), Rookavailable[rookrand].get(randmove).getY(), c[0] , c[1]);
                            moved = true;
                        }
                    }
                    break;
                    // select a king
                case 4:
                    if(Kingavailable.size() > 0){
                        int[] c = getPiece(15 , "king");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }

                        int randmove = (int) (Math.random()*Kingavailable.size());
                        System.out.println("target x ==> "+Kingavailable.get(randmove).getX()+" , y ==> "+Kingavailable.get(randmove).getY());
                        System.out.println("king at x ==> "+c[0]+" , y ==> "+c[1]);

                        simpleMove(Kingavailable.get(randmove).getX(), Kingavailable.get(randmove).getY(), c[0] , c[1]);
                        moved = true;
                    }
                    break;
                    // select a queen
                case 5:
                    if(Queenavailable.size() > 0){
                        int[] c = getPiece(16 , "queen");

                        if(c[0] == -1 && c[1] == -1){
                            break;
                        }
                        int randmove = (int) (Math.random()*Queenavailable.size());

                        System.out.println("target x ==> "+Queenavailable.get(randmove).getX()+" , y ==> "+Queenavailable.get(randmove).getY());
                        System.out.println("queen at x ==> "+c[0]+" , y ==> "+c[1]);

                        simpleMove(Queenavailable.get(randmove).getX(), Queenavailable.get(randmove).getY(), c[0] , c[1]);
                        moved = true;
                    }
                    break;
                default:
                    break;
            }
        }while(!moved);
        moved = false;
    }

    public int[] getPiece(int r , String image){
        boolean setted = false;
        int[] c = new int[2];
        for(int i=0 ; i<8 ; i++){
            for(int j=0 ; j<8 ; j++){
                try {
                    if (cells[i][j].getNumber() == r && cells[i][j].hasImage() && cells[i][j].getImage().contains("k"+image)) {
                        c[0] = i;
                        c[1] = j;
                        setted = true;
                        break;
                    }
                } catch (NullPointerException e) {
                }
            }
        }
        if(!setted){
            c[0] = -1;
            c[1] = -1;
        }
        return c;
    }

    public void simpleMove(int x , int y, int c1, int c2){
        System.err.println("image to be sit at pos "+x+" , "+y);
        if(cells[x][y].hasImage()){
            cells[x][y].setNonCharacter();
            cells[x][y].getChildren().remove(1);
        }
        cells[x][y].setImage(cells[c1][c2].getImage());
        cells[c1][c2].setNonCharacter();
        cells[c1][c2].getChildren().remove(1);
    }

    public void kill(int x1 , int y1, int x2 , int y2){
        if(cells[x2][y2].getImage().contains("kqueen")){
            Messager messager = new Messager("You win!");
        }else if (cells[x2][y2].getImage().contains("wqueen")){
            Messager messager = new Messager("You lose!");
        }else{
            cells[x2][y2].setNonCharacter();                    //throw away it's characteristics as a character
            cells[x2][y2].getChildren().remove(1);              //remove image object from the cell pane
            cells[x2][y2].setNumber(cells[x1][y1].getNumber());
            cells[x2][y2].setImage(cells[x1][y1].getImage());   //set the killer image on the cell pane

            cells[x1][y1].setNonCharacter();                    //throw away the image
            cells[x1][x1].getChildren().remove(1);              //remove image object from the cell pane
        }
    }

    private void printAvailables(ArrayList<Custom>[] available, String name) {
        for(int i=0 ; i<available.length ; i++){
            for(int j=0 ; j<available[i].size() ; j++){
                System.out.println(name+" ["+(i+1)+"] ==> "+available[i].get(j).getX()+" , "+available[i].get(j).getY());
            }
        }
    }

}
