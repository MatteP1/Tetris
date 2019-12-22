package tetris.tetrisGame.MovementStrategy;

import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;

import java.util.Map;

public interface MovementStrategy {
    Map<GridElement, Position> moveDown(Tetrimino tetrimino);
    Map<GridElement, Position> drop(PlayingField p, Tetrimino tetrimino);
    Map<GridElement, Position> moveLeft(Tetrimino tetrimino);
    Map<GridElement, Position> moveRight(Tetrimino tetrimino);
}
