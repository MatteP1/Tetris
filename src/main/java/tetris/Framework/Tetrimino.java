package tetris.Framework;

import tetris.tetrisGame.GridElement;

import java.awt.*;
import java.util.ArrayList;

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

}
