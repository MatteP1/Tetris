package tetris.Framework;

import tetris.tetrisGame.GridElement;

import java.util.List;

public interface GameObserver {
    void playFieldChangedAt(List<GridElement> grids);
}
