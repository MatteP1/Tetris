package org.tetris.tetrisGame.factories;

import org.tetris.Framework.PlayField;
import org.tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import org.tetris.tetrisGame.movementStrategy.MovementStrategy;
import org.tetris.tetrisGame.rotationStrategy.RotationStrategy;
import org.tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import org.tetris.tetrisGame.validationStrategy.ValidationStrategy;

public interface AbstractGameFactory {
    public GameOverStrategy createGameOverStrategy();
    public MovementStrategy createMovementStrategy();
    public RotationStrategy createRotationStrategy();
    public TetriminoFactory createTetriminoFactory();
    public ValidationStrategy createValidationStrategy();
    public PlayField createPlayField();
}
