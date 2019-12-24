package tetris.tetrisGame.TetriminoFactory;

import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.StandardTetrimino;

import java.awt.*;
import java.util.ArrayList;

public class FiveBlockPieceFactory implements TetriminoFactory{
    @Override
    public Tetrimino createNewTetrimino() {
        return create5LPiece();
    }

    @Override
    public Tetrimino createNewInstanceOf(Tetrimino tetrimino) {
        return create5LPiece();
    }

    public Tetrimino create5LPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,3, Color.CYAN, true));
        blocks.add(new GridElement(20,4, Color.CYAN, true));
        blocks.add(new GridElement(20,5, Color.CYAN, true));
        blocks.add(new GridElement(20,6, Color.CYAN, true));
        blocks.add(new GridElement(21,6, Color.CYAN, true));
        return new StandardTetrimino("5L", blocks, Color.CYAN, 5);
    }
}
