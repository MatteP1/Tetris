package tetrisGame.RotationStrategy;

import tetris.Position;
import tetris.Tetrimino;
import tetrisGame.GridElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StandardRotationStrategy implements RotationStrategy{

    @Override
    public Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino) {
        HashMap<GridElement, Position> result = new HashMap<>();
        int size = findEnclosingRectangleSize(tetrimino);
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = findStartX(blocks);
        int startY = findStartY(blocks);

        // (n, m) -> (m, s - n + 1)
        for (GridElement g : blocks) {
            int row = g.y() - startY;
            int col = g.x() - startX;

            int newRow = col;
            int newCol = Math.abs(size - row) + 1;

            int newRowPos = newRow + startY;
            int newColPos = newCol + startX;

            result.put(g, new Position(newRowPos, newColPos));
        }
        return result;
    }

    @Override
    public Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino) {
        HashMap<GridElement, Position> result = new HashMap<>();
        int size = tetrimino.getSize();
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = findStartX(blocks);
        int startY = findStartY(blocks);

        // (n, m) -> (s - m + 1, n)
        for (GridElement g : blocks) {
            int row = g.y() - startY;
            int col = g.x() - startX;

            int newRow = Math.abs(size - col ) + 1;
            int newCol = row;

            int newRowPos = newRow + startY;
            int newColPos = newCol + startX;

            result.put(g, new Position(newRowPos, newColPos));
        }
        return result;
    }

    private int findStartX(ArrayList<GridElement> blocks) {
        int startX = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.x() < startX) {
                startX = g.x();
            }
        }
        return startX;
    }

    private int findStartY(ArrayList<GridElement> blocks) {
        int startY = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.y() < startY) {
                startY = g.y();
            }
        }
        return startY;
    }


    private int findEndX(ArrayList<GridElement> blocks) {
        int startX = 0;
        for (GridElement g : blocks) {
            if(g.x() > startX) {
                startX = g.x();
            }
        }
        return startX;
    }

    private int findEndY(ArrayList<GridElement> blocks) {
        int startY = 0;
        for (GridElement g : blocks) {
            if(g.y() > startY) {
                startY = g.y();
            }
        }
        return startY;
    }

    private int findEnclosingRectangleSize(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        return Math.max(findEndX(blocks) - findStartX(blocks), findEndY(blocks) - findStartY(blocks)) + 1;
    }

}
