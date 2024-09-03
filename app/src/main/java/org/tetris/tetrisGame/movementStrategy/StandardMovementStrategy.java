package org.tetris.tetrisGame.movementStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.tetris.Framework.PlayField;
import org.tetris.Framework.Position;
import org.tetris.Framework.Tetrimino;
import org.tetris.tetrisGame.GridElement;
import org.tetris.util.TetriminoCalculator;

public class StandardMovementStrategy implements MovementStrategy {
    @Override
    public Map<GridElement, Position> moveDown(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.getRow()-1, block.getCol()));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> drop(PlayField p, Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        ArrayList<GridElement> ghostBlocks = TetriminoCalculator.calculateGhost(p, tetrimino);
        for (int block = 0; block < blocks.size(); block++) {
            result.put(blocks.get(block), new Position(ghostBlocks.get(block).getRow(), ghostBlocks.get(block).getCol()));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> moveLeft(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.getRow(), block.getCol()-1));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> moveRight(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.getRow(), block.getCol()+1));
        }
        return result;
    }
}
