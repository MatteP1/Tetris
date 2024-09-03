package org.tetris.tetrisGame.tetriminoFactory;

import org.tetris.Framework.Tetrimino;

public interface TetriminoFactory {
    Tetrimino createNewTetrimino();
    Tetrimino createNewInstanceOf(Tetrimino tetrimino);
}
