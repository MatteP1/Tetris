package tetris.tetrisGame.ValidationStrategy;

import tetris.Framework.PlayField;
import tetris.Framework.Position;

import java.util.ArrayList;

public interface ValidationStrategy {
    /**
     * Validates the suggested move
     * @return true if move is valid
     */
    boolean validateMove(ArrayList<Position> newPosition, PlayField playField);
}
