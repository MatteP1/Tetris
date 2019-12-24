package tetris.tetrisGame.ValidationStrategy;

import tetris.Framework.PlayField;
import tetris.Framework.Position;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.OldPlayField;
import tetris.util.TetriminoCalculator;

import java.util.ArrayList;

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
