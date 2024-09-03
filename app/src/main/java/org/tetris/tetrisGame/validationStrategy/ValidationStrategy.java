package org.tetris.tetrisGame.validationStrategy;

import java.util.ArrayList;

import org.tetris.Framework.PlayField;
import org.tetris.Framework.Position;

public interface ValidationStrategy {
    /**
     * Validates the suggested move
     * @return true if move is valid
     */
    boolean validateMove(ArrayList<Position> newPosition, PlayField playField);
}
