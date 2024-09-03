package org.tetris.tetrisGame.movementStrategy;

import java.util.Map;

import org.tetris.Framework.PlayField;
import org.tetris.Framework.Position;
import org.tetris.Framework.Tetrimino;
import org.tetris.tetrisGame.GridElement;

public interface MovementStrategy {
    Map<GridElement, Position> moveDown(Tetrimino tetrimino);
    Map<GridElement, Position> drop(PlayField p, Tetrimino tetrimino);
    Map<GridElement, Position> moveLeft(Tetrimino tetrimino);
    Map<GridElement, Position> moveRight(Tetrimino tetrimino);
}
