package tetris.tetrisGame.ValidationStrategy;

import tetris.Framework.Position;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;

import java.util.ArrayList;

public class StandardValidationStrategy implements ValidationStrategy{

    @Override
    public boolean validateMove(ArrayList<Position> newPosition, PlayingField playingField) {
        GridElement[][] grid = playingField.getGrid();
        if(!isInsidePlayingField(newPosition, playingField)) {
            return false;
        }
        for (Position p : newPosition) {
            if((grid[p.getRow()][p.getCol()]).isOccupied()) {
                return false;
            }
        }
        return true;
    }

    private boolean isInsidePlayingField(ArrayList<Position> newPosition, PlayingField playingField) {
        int colDimensions = playingField.getColDimensions() - 1;
        int rowDimensions = playingField.getRowDimensions() - 1;

        for (Position p : newPosition) {
            if((p.getCol() < 0 || p.getCol() > colDimensions) || (p.getRow() < 0 || p.getRow() > rowDimensions)) {
                return false;
            }
        }
        return true;
    }
}
