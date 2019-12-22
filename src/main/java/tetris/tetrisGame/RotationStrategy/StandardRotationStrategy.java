package tetris.tetrisGame.RotationStrategy;

import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.util.TetriminoCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static tetris.util.TetriminoCalculator.findEnclosingRectangleSize;

public class StandardRotationStrategy implements RotationStrategy{

    @Override
    public Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino) {
        HashMap<GridElement, Position> result = new HashMap<>();
        int size = findEnclosingRectangleSize(tetrimino);
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = TetriminoCalculator.findStartX(tetrimino);
        int startY = TetriminoCalculator.findStartY(tetrimino);

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
        int size = findEnclosingRectangleSize(tetrimino);
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = TetriminoCalculator.findStartX(tetrimino);
        int startY = TetriminoCalculator.findStartY(tetrimino);

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


}
