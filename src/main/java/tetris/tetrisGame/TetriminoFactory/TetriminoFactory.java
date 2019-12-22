package tetris.tetrisGame.TetriminoFactory;

import tetris.Framework.Tetrimino;

public interface TetriminoFactory {
    Tetrimino createNewTetrimino();
    Tetrimino createNewInstanceOf(Tetrimino tetrimino);
}
