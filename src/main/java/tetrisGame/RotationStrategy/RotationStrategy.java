package tetrisGame.RotationStrategy;

import tetris.Position;
import tetris.Tetrimino;
import tetrisGame.GridElement;

import java.util.ArrayList;
import java.util.Map;

public interface RotationStrategy {
    Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino);
    Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino);
}
