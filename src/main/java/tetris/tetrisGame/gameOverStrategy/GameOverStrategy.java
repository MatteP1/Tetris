package tetris.tetrisGame.gameOverStrategy;

import tetris.Framework.PlayField;

public interface GameOverStrategy {
    boolean calculateGameOver(PlayField playField);
}
