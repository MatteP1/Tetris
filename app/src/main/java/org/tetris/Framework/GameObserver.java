package org.tetris.Framework;

import java.util.List;

import org.tetris.tetrisGame.GridElement;

public interface GameObserver {
    void playFieldChangedAt(List<GridElement> grids);
}
