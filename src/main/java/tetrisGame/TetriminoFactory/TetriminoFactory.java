package tetrisGame.TetriminoFactory;

import tetris.Position;
import tetris.Tetrimino;

import java.awt.*;
import java.util.ArrayList;

public interface TetriminoFactory {
    Tetrimino createNewTetrimino();
}
