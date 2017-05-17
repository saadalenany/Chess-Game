/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.AI;

import java.awt.List;
import java.io.Serializable;

/**
 *
 * @author Saad
 * @param <K1>
 * @param <K2>
 * @param <V>
 */
public class Custom {

    private int x;
    private int y;
    private char value;
    private int selfX;
    private int selfY;

    public Custom(int x, int y, char value, int selfX, int selfY) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.selfX = selfX;
        this.selfY = selfY;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSelfX() {
        return selfX;
    }

    public void setSelfX(int selfX) {
        this.selfX = selfX;
    }

    public int getSelfY() {
        return selfY;
    }

    public void setSelfY(int selfY) {
        this.selfY = selfY;
    }

}
