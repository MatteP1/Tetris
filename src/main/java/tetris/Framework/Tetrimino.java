package tetris.Framework;

import tetris.tetrisGame.GridElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public interface Tetrimino {

    /**
     * Get the blocks that make up the piece
     * @return ArrayList of blocks
     */
    ArrayList<GridElement> getBlocks();

    /**
     * Color of the piece
     * @return Color
     */
    Color getColor();

    /**
     * The size, N, is the area in which the piece is defined.
     * @return the size of the enclosing area
     */
    int getSize();

    /**
     * Returns a string representation of the tetriminos type
     * @return name of the tetrimino type
     */
    String getType();

    /**
     * moves the piece according to the given input
     * @param wantedMove the move to be made
     */
    void applyMovement(Map<GridElement, Position> wantedMove, MovementType movementType);

    /**
     * Gets the current orientation encoded to an integer
     * @return the int representing the orientation
     */
    int getCurrentOrientation();


}
