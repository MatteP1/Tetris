package tetris.tetrisGame;

import tetris.Framework.Tetrimino;

import java.awt.*;
import java.util.ArrayList;

public class StandardTetrimino implements Tetrimino {

    private int size;
    private ArrayList<GridElement> blocks;
    private Color color;

    public StandardTetrimino(ArrayList<GridElement> blocks, Color color, int size) {
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
}
