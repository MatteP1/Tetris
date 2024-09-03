package org.tetris;
import org.tetris.Framework.PlayField;
import org.tetris.tetrisGame.StandardPlayField;
import org.tetris.tetrisGame.factories.AbstractGameFactory;
import org.tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import org.tetris.tetrisGame.gameOverStrategy.LosingStrategy;
import org.tetris.tetrisGame.movementStrategy.MovementStrategy;
import org.tetris.tetrisGame.movementStrategy.StandardMovementStrategy;
import org.tetris.tetrisGame.rotationStrategy.MatrixStyleRotationStrategy;
import org.tetris.tetrisGame.rotationStrategy.RotationStrategy;
import org.tetris.tetrisGame.tetriminoFactory.SingleTypeTetriminoFactory;
import org.tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import org.tetris.tetrisGame.validationStrategy.StandardValidationStrategy;
import org.tetris.tetrisGame.validationStrategy.ValidationStrategy;

public class TestingGameFactory implements AbstractGameFactory {
    @Override
    public GameOverStrategy createGameOverStrategy() {
        return new LosingStrategy();
    }

    @Override
    public MovementStrategy createMovementStrategy() {
        return new StandardMovementStrategy();
    }

    @Override
    public RotationStrategy createRotationStrategy() {
        return new MatrixStyleRotationStrategy();
    }

    @Override
    public TetriminoFactory createTetriminoFactory() {
        return new SingleTypeTetriminoFactory(SingleTypeTetriminoFactory.TetriminoType.TYPE_I);
    }

    @Override
    public ValidationStrategy createValidationStrategy() {
        return new StandardValidationStrategy();
    }

    @Override
    public PlayField createPlayField() {
        return new StandardPlayField();
    }
}
