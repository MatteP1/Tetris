package tetris.tetrisGame.GameOverStrategy;

import tetris.Framework.PlayField;
import tetris.tetrisGame.GridElement;

public class LosingStrategy implements GameOverStrategy{

    @Override
    public boolean calculateGameOver(PlayField playField) {
        for(GridElement g : playField.getBlocksInRow(20)) {
            if(g.isOccupied()){
                return true;
            }
        }
        return false;
    }

}
