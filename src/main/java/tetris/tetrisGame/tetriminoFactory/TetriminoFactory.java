package tetris.tetrisGame.tetriminoFactory;

import tetris.Framework.Tetrimino;

public interface TetriminoFactory {
    Tetrimino createNewTetrimino();
    Tetrimino createNewInstanceOf(Tetrimino tetrimino);
}
