package tetris.tetrisGame;

import java.awt.Color;
import javax.swing.*;

/**
 * Represents an element in the playingFieldGrid
 * @author MatRusTy
 */
public class GridElement extends JPanel{

    // --------------------- FIELD VARIABLES ---------------------
    private boolean occupied;
    private int row;
    private int col;

    /**
     * Creates a new GridElement with the specified parameters.
     * @param row The y coordinate of the gridElement.
     * @param col The x coordinate of the gridElement.
     * @param color The color of the gridElement.
     * @param o Is the gridElement occupied (can a tetrimino be in this spot).
     */
    public GridElement(int row, int col, Color color, boolean o){
        occupied = o;
        this.col = col;
        this.row = row;
        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    // --------------------- GETTERS AND SETTERS ---------------------
    public boolean isOccupied() {
        return occupied;
    }
    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }
    public void setCol(int col){
        this.col = col;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void makeOccupied(){
        occupied = true;
    }
    public void setOccupied(boolean b){
        occupied = b;
    }
}
