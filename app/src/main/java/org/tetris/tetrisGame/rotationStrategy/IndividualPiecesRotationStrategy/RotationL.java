package org.tetris.tetrisGame.rotationStrategy.IndividualPiecesRotationStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.tetris.Framework.Position;
import org.tetris.Framework.Tetrimino;
import org.tetris.tetrisGame.GridElement;
import org.tetris.tetrisGame.rotationStrategy.RotationStrategy;

/**
 * Class representing the L-piece
 */
public class RotationL implements RotationStrategy {

    private int zeroy, zerox, oney, onex, twoy, twox, threey, threex;
    private GridElement zero, one, two, three;

    @Override
    public Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        zero = blocks.get(0);
        one = blocks.get(1);
        two = blocks.get(2);
        three = blocks.get(3);

        switch (tetrimino.getCurrentOrientation()) {
            case 0: rotateClockwiseCase0(); break;
            case 1: rotateClockwiseCase1(); break;
            case 2: rotateClockwiseCase2(); break;
            case 3: rotateClockwiseCase3(); break;
        }

        result.put(zero, new Position(zeroy, zerox));
        result.put(one, new Position(oney, onex));
        result.put(two, new Position(twoy, twox));
        result.put(three, new Position(threey, threex));

        return result;
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
    @Override
    public Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        zero = blocks.get(0);
        one = blocks.get(1);
        two = blocks.get(2);
        three = blocks.get(3);

        switch (tetrimino.getCurrentOrientation()){
            case 3 : rotateClockwiseCase0(); break;
            case 0 : rotateClockwiseCase1(); break;
            case 1 : rotateClockwiseCase2(); break;
            case 2 : rotateClockwiseCase3(); break;
        }
        result.put(zero, new Position(zeroy, zerox));
        result.put(one, new Position(oney, onex));
        result.put(two, new Position(twoy, twox));
        result.put(three, new Position(threey, threex));

        return result;
    }

    public void rotateClockwiseCase0() {
        zeroy = zero.getRow()+1;
        zerox = zero.getCol()+1;

        oney = one.getRow();
        onex = one.getCol();

        twoy = two.getRow()-1;
        twox = two.getCol()-1;

        threey = three.getRow()-2;
        threex = three.getCol();
    }

    public void rotateClockwiseCase1() {
        zeroy = zero.getRow()-1;
        zerox = zero.getCol()+1;

        oney = one.getRow();
        onex = one.getCol();

        twoy = two.getRow()+1;
        twox = two.getCol()-1;

        threey = three.getRow();
        threex = three.getCol()-2;
    }

    public void rotateClockwiseCase2() {
        zeroy = zero.getRow()-1;
        zerox = zero.getCol()-1;

        oney = one.getRow();
        onex = one.getCol();

        twoy = two.getRow()+1;
        twox = two.getCol()+1;

        threey = three.getRow()+2;
        threex = three.getCol();
    }

    public void rotateClockwiseCase3() {
        zeroy = zero.getRow()+1;
        zerox = zero.getCol()-1;

        oney = one.getRow();
        onex = one.getCol();

        twoy = two.getRow()-1;
        twox = two.getCol()+1;

        threey = three.getRow();
        threex = three.getCol()+2;
    }

    public String toString(){
        return "L-piece";
    }

}
