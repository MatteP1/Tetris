package tetris.tetrisGame.factories;

import tetris.Framework.PlayField;
import tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import tetris.tetrisGame.movementStrategy.MovementStrategy;
import tetris.tetrisGame.rotationStrategy.RotationStrategy;
import tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import tetris.tetrisGame.validationStrategy.ValidationStrategy;

public interface AbstractGameFactory {
    public GameOverStrategy createGameOverStrategy();
    public MovementStrategy createMovementStrategy();
    public RotationStrategy createRotationStrategy();
    public TetriminoFactory createTetriminoFactory();
    public ValidationStrategy createValidationStrategy();
    public PlayField createPlayField();
}
