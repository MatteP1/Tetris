package org.tetris.tetrisGame.rotationStrategy;

import java.util.Map;

import org.tetris.Framework.Position;
import org.tetris.Framework.Tetrimino;
import org.tetris.tetrisGame.GridElement;

public interface RotationStrategy {
    Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino);
    Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino);
}
