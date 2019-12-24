package tetris.tetrisGame.RotationStrategy;

import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.util.TetriminoCalculator;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class MatrixStyleRotationStrategy implements RotationStrategy{
    @Override
    public Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino) {
        return null;
    }

    @Override
    public Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino) {
        return null;
    }

    private Vector<Vector<Integer>> matrixify(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startY = TetriminoCalculator.findStartY(tetrimino);
        int startX = TetriminoCalculator.findStartX(tetrimino);
        int matrixSize = TetriminoCalculator.findEnclosingRectangleSize(tetrimino);
        Vector<Vector<Integer>> matrix = new Vector<Vector<Integer>>(matrixSize);
        // orientation 0 : piece in top left
        // orientation 1 : piece in top right
        // orientation 2 : piece in bottom right
        // orientation 3 : piece in bottom left
        for (int i = 0; i < matrixSize; i++) {
            Vector<Integer> row = new Vector<>(matrixSize);
            for (int j = 0; j < matrixSize; j++) {
                for (GridElement g : blocks) {
                    if(g.getRow() - startY == j) {
                        //TODO: DOESN'T WORK, PLZ FIX
                    }
                }
            }
        }


        return null;
    }
}
