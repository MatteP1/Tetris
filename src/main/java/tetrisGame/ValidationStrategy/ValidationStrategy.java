package tetrisGame.ValidationStrategy;

import tetris.Position;
import tetrisGame.GridElement;

import java.util.ArrayList;

public interface ValidationStrategy {
    /**
     * Validates the suggested move
     * @return true if move is valid
     */
    boolean validateMove(ArrayList<Position> newPosition, GridElement[][] playingField);
}
