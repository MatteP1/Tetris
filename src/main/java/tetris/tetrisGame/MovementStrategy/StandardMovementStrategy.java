package tetris.tetrisGame.MovementStrategy;

import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;
import tetris.util.TetriminoCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StandardMovementStrategy implements MovementStrategy {
    @Override
    public Map<GridElement, Position> moveDown(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.y()-1, block.x()));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> drop(PlayingField p, Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        ArrayList<GridElement> ghostBlocks = TetriminoCalculator.calculateGhost(p, tetrimino);
        for (int block = 0; block < blocks.size(); block++) {
            result.put(blocks.get(block), new Position(ghostBlocks.get(block).y(), ghostBlocks.get(block).x()));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> moveLeft(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.y(), block.x()-1));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> moveRight(Tetrimino tetrimino) {
        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for (GridElement block : blocks) {
            result.put(block, new Position(block.y(), block.x()+1));
        }
        return result;
    }
}
