package org.tetris.tetrisGame.validationStrategy;

import java.util.ArrayList;

import org.tetris.Framework.PlayField;
import org.tetris.Framework.Position;
import org.tetris.tetrisGame.GridElement;
import org.tetris.util.TetriminoCalculator;

public class StandardValidationStrategy implements ValidationStrategy{

    @Override
    public boolean validateMove(ArrayList<Position> newPosition, PlayField playField) {
        GridElement[][] grid = TetriminoCalculator.playFieldConverter(playField.getGrid());
        if(!isInsidePlayingField(newPosition, playField)) {
            return false;
        }
        for (Position p : newPosition) {
            if((grid[p.getRow()][p.getCol()]).isOccupied()) {
                return false;
            }
        }
        return true;
    }

    private boolean isInsidePlayingField(ArrayList<Position> newPosition, PlayField playField) {
        int colDimensions = playField.getColDimensions() - 1;
        int rowDimensions = playField.getRowDimensions() - 1;

        for (Position p : newPosition) {
            if((p.getCol() < 0 || p.getCol() > colDimensions) || (p.getRow() < 0 || p.getRow() > rowDimensions)) {
                return false;
            }
        }
        return true;
    }
}
