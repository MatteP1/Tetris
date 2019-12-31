package tetris.util;

import tetris.Framework.PlayField;
import tetris.Framework.Position;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class TetriminoCalculator {



    /**
     * calculates the values of the ghost piece, that is, the position of the current tetrimino if it is dropped.
     * returns empty playlist if closestToOccupiedSlot is 0
     */
    public static ArrayList<GridElement> calculateGhost(PlayField p, Tetrimino t) {
        GridElement[][] grid = playFieldConverter(p.getGrid());
        ArrayList<GridElement> blocks = t.getBlocks();
        int closestToOccupiedSlot = Integer.MAX_VALUE;
        for(GridElement g : blocks){
            closestToOccupiedSlot = Math.min(g.getRow(), closestToOccupiedSlot);
        }

        for(GridElement g : blocks){
            for (int i = 1; i <= g.getRow(); i++) {
                if (grid[g.getRow()-i][g.getCol()].isOccupied()){
                    closestToOccupiedSlot = Math.min(i-1, closestToOccupiedSlot);
                }
            }
        }

        ArrayList<GridElement> ghostBlocks = new ArrayList<>(4);

        for(GridElement g : blocks){
            ghostBlocks.add(grid[g.getRow()-closestToOccupiedSlot][g.getCol()]);
        }

        return ghostBlocks;
    }

    public static GridElement[][] playFieldConverter(Iterable<Iterable<GridElement>> gridItIt) {
        Iterator<Iterable<GridElement>> gridItIterator = gridItIt.iterator();
        int rows = (int) StreamSupport.stream(gridItIt.spliterator(), false).count();
        int cols = (int) StreamSupport.stream(gridItIt.iterator().next().spliterator(), false).count();
        GridElement[][] grid = new GridElement[rows][cols];
        while (gridItIterator.hasNext()) {
            for (GridElement g : gridItIterator.next()) {
                grid[g.getRow()][g.getCol()] = g;
            }
        }
        return grid;
    }


    public static int findStartX(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.getCol() < startX) {
                startX = g.getCol();
            }
        }
        return startX;
    }


    public static int findStartY(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startY = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.getRow() < startY) {
                startY = g.getRow();
            }
        }
        return startY;
    }


    public static int findEndX(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int endX = 0;
        for (GridElement g : blocks) {
            if(g.getCol() > endX) {
                endX = g.getCol();
            }
        }
        return endX;
    }

    public static int findEndY(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int endY = 0;
        for (GridElement g : blocks) {
            if(g.getRow() > endY) {
                endY = g.getRow();
            }
        }
        return endY;
    }

    public static int findLengthY(Tetrimino tetrimino) {
        return Math.abs(findEndY(tetrimino) - findStartY(tetrimino)) + 1;
    }

    public static int findLengthX(Tetrimino tetrimino) {
        return Math.abs(findEndX(tetrimino) - findStartX(tetrimino)) + 1;
    }

    public static int findEnclosingRectangleSize(Tetrimino tetrimino) {
        return Math.max(findEndX(tetrimino) - findStartX(tetrimino), findEndY(tetrimino) - findStartY(tetrimino)) + 1;
    }


    public static Position findPieceCenter(Tetrimino tetrimino) {
        int lengthY = findLengthY(tetrimino);
        int lengthX = findLengthX(tetrimino);
        int orientation = tetrimino.getCurrentOrientation();
        // TODO: TEST THIS METHOD (by giving it tetriminoes in different orientations and asserting center)
        int centerY = ((lengthY-1) / 2) + (findStartY(tetrimino));
        int centerX = ((lengthX-1) / 2) + (findStartX(tetrimino));
//        if(lengthX % 2 == 0) {
//            centerX += (orientation == 0 || orientation == 3) ? 0 : -1;
//        }
//        if(lengthY % 2 == 0) {
//            centerY += (orientation == 0 || orientation == 1) ? -1 : 0;
//        }
        return new Position(centerY,centerX);
    }

    // orientation 0 : piece in top left
    // orientation 1 : piece in top right
    // orientation 2 : piece in bottom right
    // orientation 3 : piece in bottom left

}
