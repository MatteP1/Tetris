import tetris.Framework.PlayField;
import tetris.tetrisGame.StandardPlayField;
import tetris.tetrisGame.factories.AbstractGameFactory;
import tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import tetris.tetrisGame.gameOverStrategy.LosingStrategy;
import tetris.tetrisGame.movementStrategy.MovementStrategy;
import tetris.tetrisGame.movementStrategy.StandardMovementStrategy;
import tetris.tetrisGame.rotationStrategy.MatrixStyleRotationStrategy;
import tetris.tetrisGame.rotationStrategy.RotationStrategy;
import tetris.tetrisGame.tetriminoFactory.SingleTypeTetriminoFactory;
import tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import tetris.tetrisGame.validationStrategy.StandardValidationStrategy;
import tetris.tetrisGame.validationStrategy.ValidationStrategy;

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
