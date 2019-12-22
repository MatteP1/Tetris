package tetris.util;

import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.PlayingField;

import java.util.ArrayList;

public class TetriminoCalculator {



    /**
     * calculates the values of the ghost piece, that is, the position of the current tetrimino if it is dropped.
     * returns empty playlist if closestToOccupiedSlot is 0
     */
    public static ArrayList<GridElement> calculateGhost(PlayingField p, Tetrimino t) {
        GridElement[][] grid = p.getGrid();
        ArrayList<GridElement> blocks = t.getBlocks();
        int closestToOccupiedSlot = Integer.MAX_VALUE;
        for(GridElement g : blocks){
            closestToOccupiedSlot = Math.min(g.y(), closestToOccupiedSlot);
        }

        for(GridElement g : blocks){
            for (int i = 1; i <= g.y(); i++) {
                if (grid[g.y()-i][g.x()].isOccupied()){
                    closestToOccupiedSlot = Math.min(i-1, closestToOccupiedSlot);
                }
            }
        }

        ArrayList<GridElement> ghostBlocks = new ArrayList<>(4);

        for(GridElement g : blocks){
            ghostBlocks.add(grid[g.y()-closestToOccupiedSlot][g.x()]);
        }

        return ghostBlocks;
    }


    public static int findStartX(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.x() < startX) {
                startX = g.x();
            }
        }
        return startX;
    }


    public static int findStartY(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startY = Integer.MAX_VALUE;
        for (GridElement g : blocks) {
            if(g.y() < startY) {
                startY = g.y();
            }
        }
        return startY;
    }


    public static int findEndX(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startX = 0;
        for (GridElement g : blocks) {
            if(g.x() > startX) {
                startX = g.x();
            }
        }
        return startX;
    }

    public static int findEndY(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        int startY = 0;
        for (GridElement g : blocks) {
            if(g.y() > startY) {
                startY = g.y();
            }
        }
        return startY;
    }

    public static int findEnclosingRectangleSize(Tetrimino tetrimino) {
        return Math.max(findEndX(tetrimino) - findStartX(tetrimino), findEndY(tetrimino) - findStartY(tetrimino)) + 1;
    }


}
