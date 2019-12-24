package tetris.tetrisGame.GameOverStrategy;

import tetris.Framework.PlayField;

public interface GameOverStrategy {
    boolean calculateGameOver(PlayField playField);
}
