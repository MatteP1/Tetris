package tetris.tetrisGame.ValidationStrategy;

import tetris.Framework.Position;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;

import java.util.ArrayList;

public class StandardValidationStrategy implements ValidationStrategy{

    @Override
    public boolean validateMove(ArrayList<Position> newPosition, PlayingField playingField) {
        GridElement[][] grid = playingField.getGrid();
        for (Position p : newPosition) {
            if((grid[p.getRow()][p.getCol()]).isOccupied()) {
                return false;
            }
            //TODO: check that new move is within playable area.
        }
        return true;
    }
}
