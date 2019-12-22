package tetris.tetrisGame.RotationStrategy;

import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;

import java.util.Map;

public interface RotationStrategy {
    Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino);
    Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino);
}
