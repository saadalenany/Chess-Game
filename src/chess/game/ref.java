/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

/**
 *
 * @author Saad
 */
public class ref {

    public static Paint cadeTblue = Color.CADETBLUE;

//            new RadialGradient(0,
//            .1,
//            100,
//            100,
//            20,
//            false,
//            CycleMethod.NO_CYCLE,
//            new Stop(0, Color.CADETBLUE),
//            new Stop(1.0f, Color.rgb(0, 0, 0, .1)));

    public static Paint antiqueWhite = Color.ANTIQUEWHITE;

    //hovering color
    public static Paint hover = Color.CYAN;

    //killing color
    public static Paint killing = Color.BROWN;

    //pawn dynamic killing move
    public static Paint dynamic = Color.CHOCOLATE;

    //array 2D of cells
    public static Cell[][] cells;

    public static int NUMBER_OF_PAWNS = 8;

    public static int NUMBER_OF_ROOKS = 2;

    public static int NUMBER_OF_BISHOPS = 2;

    public static int NUMBER_OF_KNIGHTS = 2;

}
