package tetris.tetrisGame.ValidationStrategy;

import tetris.Framework.Position;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;

import java.util.ArrayList;

public interface ValidationStrategy {
    /**
     * Validates the suggested move
     * @return true if move is valid
     */
    boolean validateMove(ArrayList<Position> newPosition, PlayingField playingField);
}
