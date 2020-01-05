package tetris.tetrisGame.factories;

import tetris.Framework.PlayField;
import tetris.tetrisGame.OldPlayField;
import tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import tetris.tetrisGame.gameOverStrategy.LosingStrategy;
import tetris.tetrisGame.movementStrategy.MovementStrategy;
import tetris.tetrisGame.movementStrategy.StandardMovementStrategy;
import tetris.tetrisGame.rotationStrategy.IndividualPieceRotationStrategy;
import tetris.tetrisGame.rotationStrategy.RotationStrategy;
import tetris.tetrisGame.tetriminoFactory.StandardTetriminoFactory;
import tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import tetris.tetrisGame.validationStrategy.StandardValidationStrategy;
import tetris.tetrisGame.validationStrategy.ValidationStrategy;

public class TetrisV1Factory implements AbstractGameFactory{

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
        return new IndividualPieceRotationStrategy();
    }

    @Override
    public TetriminoFactory createTetriminoFactory() {
        return new StandardTetriminoFactory();
    }

    @Override
    public ValidationStrategy createValidationStrategy() {
        return new StandardValidationStrategy();
    }

    @Override
    public PlayField createPlayField() {
        return new OldPlayField();
    }
}
