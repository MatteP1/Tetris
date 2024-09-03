package org.tetris.tetrisGame.gameOverStrategy;

import org.tetris.Framework.PlayField;

public interface GameOverStrategy {
    boolean calculateGameOver(PlayField playField);
}
