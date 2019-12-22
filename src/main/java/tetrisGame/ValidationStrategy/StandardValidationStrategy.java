package tetrisGame.ValidationStrategy;

import tetris.Position;
import tetrisGame.GridElement;

import java.util.ArrayList;

public class StandardValidationStrategy implements ValidationStrategy{

    @Override
    public boolean validateMove(ArrayList<Position> newPosition, GridElement[][] playingField) {
        for (Position p : newPosition) {
            if((playingField[p.getRow()][p.getCol()]).isOccupied()) {
                return false;
            }
        }
        return true;
    }
}
