package tetris.tetrisGame;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This class represents the generic type: tetris.Framework.Tetrimino. Sub-classes are actual tetriminos.
 * @author MatRusTy
 */
public abstract class TetriminoOld {

    // --------------------- FIELD VARIABLES ---------------------
    private int orientation;
    private Color color;
    protected ArrayList<GridElement> pieces; //position of the 4 pieces
    protected PlayingField playfield;
    protected GridElement[][] grid;

    //----- PIECE NUMBERS -----
    protected GridElement zero;
    protected GridElement one;
    protected GridElement two;
    protected GridElement three;

    //----- NEW COORDS -----
    protected int zeroy=0;
    protected int zerox=0;

    protected int oney=0;
    protected int onex=0;

    protected int twoy=0;
    protected int twox=0;

    protected int threey=0;
    protected int threex=0;

    // --------------------- CONSTRUCTOR ---------------------

    /**
     * Sets the generic values for a tetrimino
     * @param c Color of the tetrimino
     * @param p The playingfield on which the tetrimino will be played.
     */
    public TetriminoOld(Color c, PlayingField p){
        orientation = 0;
        color = c;
        pieces = new ArrayList<>();
        playfield = p;
        grid = p.getGrid();
    }

    // --------------------- MOVEMENT LOGIC ---------------------

    /**
     * Calls the overwritten method in the sub-classes, and then they set the NEW COORDS field variables to the right values.
     * The value of these field variables are then applied if possible, that is, if they are legal positions in the current playinfield.
     */
    public void rotateClockwise(){
        switch (orientation){
            case 0 : rotateClockwiseCase0(); break;
            case 1 : rotateClockwiseCase1(); break;
            case 2 : rotateClockwiseCase2(); break;
            case 3 : rotateClockwiseCase3(); break;
        }
        if(applyNewCoords()){
            if(orientation == 3){
                orientation = 0;
            } else {
                orientation++;
            }
        }

        for(GridElement g : pieces){
            System.out.print("(" + g.getRow() +", "+  g.getCol() + ") ");
        }
        System.out.println();
        System.out.println("now in orientation: " + orientation);
        System.out.println();

    }


    /**
     * This method uses the fact that there is a connection between the rotations and orientations of the cw and ccw rotations
     * Rotation Pairs (the number indicates the current orientation of the tetrimino) :
     * cw	ccw
     * 0	3
     * 1	0
     * 2	1
     * 3	2
     * These rotation methods do, computationally, the same, therefore ccw just calls the cw rotations
     * in those specific orientations / cases.
     */
    public void rotateCounterClockwise(){
        switch (orientation){
            case 3 : rotateClockwiseCase0(); break;
            case 0 : rotateClockwiseCase1(); break;
            case 1 : rotateClockwiseCase2(); break;
            case 2 : rotateClockwiseCase3(); break;
        }
        if(applyNewCoords()){
            if(orientation == 0){
                orientation = 3;
            } else {
                orientation--;
            }
        }

        for(GridElement g : pieces){
            System.out.print("(" + g.getRow() +", "+  g.getCol() + ") ");
        }
        System.out.println();
        System.out.println("now in orientation: " + orientation);
        System.out.println();
    }

    /**
     * Moves the tetrimino one unit to the left
     */
    public void moveLeft(){
        zeroy = zero.getRow();
        zerox = zero.getCol()-1;

        oney = one.getRow();
        onex = one.getCol()-1;

        twoy = two.getRow();
        twox = two.getCol()-1;

        threey = three.getRow();
        threex = three.getCol()-1;
        applyNewCoords();
    }

    /**
     * Moves the tetrimino one unit to the right
     */
    public void moveRight(){
        zeroy = zero.getRow();
        zerox = zero.getCol()+1;

        oney = one.getRow();
        onex = one.getCol()+1;

        twoy = two.getRow();
        twox = two.getCol()+1;

        threey = three.getRow();
        threex = three.getCol()+1;
        applyNewCoords();
    }


    /**
     * Makes the current tetrimino moveDown down 1 row if there is space for it.
     * @return Boolean expresion telling if the move was successful
     */
    public boolean moveDown() {
        zeroy = zero.getRow()-1;
        zerox = zero.getCol();

        oney = one.getRow()-1;
        onex = one.getCol();

        twoy = two.getRow()-1;
        twox = two.getCol();

        threey = three.getRow()-1;
        threex = three.getCol();
        return applyNewCoords();
    }


    public void drop(){
        ArrayList<GridElement> newPieces = calculateGhost();

        zeroy = newPieces.get(0).getRow();
        zerox = newPieces.get(0).getCol();

        oney = newPieces.get(1).getRow();
        onex = newPieces.get(1).getCol();

        twoy = newPieces.get(2).getRow();
        twox = newPieces.get(2).getCol();

        threey = newPieces.get(3).getRow();
        threex = newPieces.get(3).getCol();

        applyNewCoords();

    }

    /**
     * calculates the values of the ghost piece, that is, the position of the current tetrimino if it is dropped.
     * returns empty playlist if closestToOccupiedSlot is 0
     */
    public ArrayList<GridElement> calculateGhost(){
        int closestToOccupiedSlot = Integer.MAX_VALUE;
        for(GridElement g : pieces){
            closestToOccupiedSlot = Math.min(g.getRow(), closestToOccupiedSlot);
        }

        for(GridElement g : pieces){
            for (int i = 1; i <= g.getRow(); i++) {
                if (grid[g.getRow()-i][g.getCol()].isOccupied()){
                    closestToOccupiedSlot = Math.min(i-1, closestToOccupiedSlot);
                }
            }
        }

        ArrayList<GridElement> ghostPieces = new ArrayList<>(4);

        for(GridElement g : pieces){
            ghostPieces.add(grid[g.getRow()-closestToOccupiedSlot][g.getCol()]);
        }

        return ghostPieces;
    }


    // --------------------- METHODS TO BE OVERRIDDEN ---------------------
    public abstract void rotateClockwiseCase0();
    public abstract void rotateClockwiseCase1();
    public abstract void rotateClockwiseCase2();
    public abstract void rotateClockwiseCase3();

    // --------------------- HELPER METHODS FOR THE GAME LOGIC ---------------------

    /**
     * Applies the new coordinates if they are legal.
     * @return
     */
    private boolean applyNewCoords(){
        if(isLegal(zeroy, zerox, oney, onex, twoy, twox, threey, threex)) {
            zero.setRow(zeroy);
            zero.setCol(zerox);

            one.setRow(oney);
            one.setCol(onex);

            two.setRow(twoy);
            two.setCol(twox);

            three.setRow(threey);
            three.setCol(threex);
            return true;
        }
        return false;
    }

    /**
     * Tests if the specified coordinates are legal, ie. not occupied.
     * @param zeroy y-coordinate of the 0th piece
     * @param zerox x-coordinate of the 0th piece
     *
     * @param oney y-coordinate of the 1st piece
     * @param onex x-coordinate of the 1st piece
     *
     * @param twoy y-coordinate of the 2nd piece
     * @param twox x-coordinate of the 2nd piece
     *
     * @param threey y-coordinate of the 3rd piece
     * @param threex x-coordinate of the 3rd piece
     *
     * @return a boolean value telling if the coordinates are legal.
     */
    private boolean isLegal(int zeroy, int zerox,int oney, int onex,int twoy, int twox,int threey, int threex){
        if(zerox < 0 || zerox > 9 || onex < 0 || onex > 9 || twox < 0 || twox > 9 || threex < 0 || threex > 9){
            return false;
        } else if(zeroy < 0 || oney < 0 || twoy < 0 || threey < 0) {
            return false;
        } else if (grid[zeroy][zerox].isOccupied() || grid[oney][onex].isOccupied() || grid[twoy][twox].isOccupied() || grid[threey][threex].isOccupied()) {
            return false;
        } else {
            return true;
        }
    }

    // --------------------- GETTERS ---------------------

    /**
     * @return The pieces making up the tetrimino
     */
    public ArrayList<GridElement> getPieces() {
        return pieces;
    }
}