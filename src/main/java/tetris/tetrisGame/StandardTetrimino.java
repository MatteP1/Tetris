package tetris.tetrisGame;

import tetris.Framework.MovementType;
import tetris.Framework.Position;
import tetris.Framework.Tetrimino;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class StandardTetrimino implements Tetrimino {

    private String type;
    private int size;
    private ArrayList<GridElement> blocks;
    private Color color;
    private int currentOrientation;

    public StandardTetrimino(String type, ArrayList<GridElement> blocks, Color color, int size) {
        this.type = type;
        this.blocks = blocks;
        this.color = color;
        this.size = size;
    }

    @Override
    public ArrayList<GridElement> getBlocks() {
        return blocks;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void applyMovement(Map<GridElement, Position> wantedMove, MovementType movementType) {
        Set<GridElement> blocks = wantedMove.keySet();
        if(!(this.blocks.containsAll(blocks))) {
            return;
        }
        for (GridElement block : blocks) {
            Position newPosition = wantedMove.get(block);
            block.setCol(newPosition.getCol());
            block.setRow(newPosition.getRow());
        }
        // Update orientation variable
        if(movementType == MovementType.ROTATE_CW) {
            cRotation();
        } else if(movementType == MovementType.ROTATE_CCW) {
            ccRotation();
        }
    }

    /**
     * 0: orientation at start, 1: -90deg, 2: -180deg, 3: -270deg
     * @return
     */
    @Override
    public int getCurrentOrientation() {
        return currentOrientation;
    }

    private void cRotation() {
        if(currentOrientation < 3) {
            currentOrientation++;
        } else {
            currentOrientation = 0;
        }
    }

    private void ccRotation() {
        if(currentOrientation > 0) {
            currentOrientation--;
        } else {
            currentOrientation = 3;
        }
    }

    @Override
    public String toString() {
        return getType() + "-piece";
    }
}
