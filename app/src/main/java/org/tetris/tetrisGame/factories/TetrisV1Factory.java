package org.tetris.tetrisGame.factories;

import org.tetris.Framework.PlayField;
import org.tetris.tetrisGame.OldPlayField;
import org.tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import org.tetris.tetrisGame.gameOverStrategy.LosingStrategy;
import org.tetris.tetrisGame.movementStrategy.MovementStrategy;
import org.tetris.tetrisGame.movementStrategy.StandardMovementStrategy;
import org.tetris.tetrisGame.rotationStrategy.IndividualPieceRotationStrategy;
import org.tetris.tetrisGame.rotationStrategy.RotationStrategy;
import org.tetris.tetrisGame.tetriminoFactory.StandardTetriminoFactory;
import org.tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import org.tetris.tetrisGame.validationStrategy.StandardValidationStrategy;
import org.tetris.tetrisGame.validationStrategy.ValidationStrategy;

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
