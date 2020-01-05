package tetris.tetrisGame.rotationStrategy;

import org.ejml.simple.SimpleMatrix;
import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.util.TetriminoCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatrixStyleRotationStrategy implements RotationStrategy{
    @Override
    public Map<GridElement, Position> rotateClockWise(Tetrimino tetrimino) {
        int startY = TetriminoCalculator.findStartY(tetrimino);
        int endY = TetriminoCalculator.findEndY(tetrimino);
        int startX = TetriminoCalculator.findStartX(tetrimino);
        int endX = TetriminoCalculator.findEndX(tetrimino);
        int matrixSize = TetriminoCalculator.findEnclosingRectangleSize(tetrimino);
        SimpleMatrix matrix = matrixify(tetrimino);
        // perform rotation:
        // First transpose, then mirror horizontally
        SimpleMatrix transpose = matrix.transpose();

        // Mirror matrix:
        SimpleMatrix mirrorMatrix = new SimpleMatrix(matrixSize, matrixSize);
        mirrorMatrix.fill(0);
        for (int i = 0; i < matrixSize; i++) {
            mirrorMatrix.set(i, (matrixSize-1)-i, 1);
        }
        SimpleMatrix rotatedMatrix = transpose.mult(mirrorMatrix);

        Map<GridElement, Position> result = new HashMap<>();
        ArrayList<Position> positions = new ArrayList<>();

        Position center = TetriminoCalculator.findPieceCenter(tetrimino);
        int matrixStartY = center.getRow() - matrixSize / 2;
        int matrixStartX = center.getCol() - matrixSize / 2;
        System.out.println("Y " + matrixStartY + "   AND " + center.getRow());
        System.out.println("X " + matrixStartX + "   AND " + center.getCol());
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                if(rotatedMatrix.get(row, col) == 1) {
                    positions.add(new Position(matrixStartY + ((matrixSize-1) - row), matrixStartX + col));
                }
            }
        }
        int i = 0;
        for (GridElement g : tetrimino.getBlocks()) {
            result.put(g, positions.get(i));
            i++;
        }
        System.out.println(rotatedMatrix);

        return result;
    }

    @Override
    public Map<GridElement, Position> rotateCounterClockWise(Tetrimino tetrimino) {
        return null;
    }

    private SimpleMatrix matrixify(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startY = TetriminoCalculator.findStartY(tetrimino);
        int endY = TetriminoCalculator.findEndY(tetrimino);
        int startX = TetriminoCalculator.findStartX(tetrimino);
        int endX = TetriminoCalculator.findEndX(tetrimino);
        int matrixSize = TetriminoCalculator.findEnclosingRectangleSize(tetrimino);
        int orientation = tetrimino.getCurrentOrientation();
        SimpleMatrix matrix = new SimpleMatrix(matrixSize, matrixSize);
        matrix.fill(0);

        // orientation 0 : piece in top left
        // orientation 1 : piece in top right
        // orientation 2 : piece in bottom right
        // orientation 3 : piece in bottom left
        if(orientation == 0) {
            for (GridElement g : blocks) {
                matrix.set(endY - g.getRow(), g.getCol() - startX, 1);
            }
        } else if(orientation == 1) {
            for (GridElement g : blocks) {
                matrix.set(endY - g.getRow(), (matrixSize-1) - (endX - g.getCol()), 1);
            }
        } else if(orientation == 2) {
            for (GridElement g : blocks) {
                matrix.set((matrixSize-1) - (g.getRow() - startY), (matrixSize-1) - (endX - g.getCol()), 1);
            }
        } else if(orientation == 3) {
            for (GridElement g : blocks) {
                matrix.set((matrixSize-1) - (g.getRow() - startY), g.getCol() - startX, 1);
            }
        }
        System.out.println(matrix);
        return matrix;
    }
}
