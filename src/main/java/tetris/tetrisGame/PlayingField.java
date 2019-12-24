package tetris.tetrisGame;

import tetris.Framework.Tetrimino;

import java.util.*;
import java.awt.Color;

/**
 * Class representing the playingfield.
 * @author MatRusTy
 */
public class PlayingField {
    private GridElement[][] grid;


    public PlayingField() {
        grid = new GridElement[40][10];
        clearGrid();
    }

    /**
     * Sets up the grid with empty GridElements
     */
    public void clearGrid() {
        for (int i = 0; i <= 39; i++) {
            for (int j = 0; j <= 9; j++) {
                grid[i][j] = new GridElement(i, j, Color.GRAY.darker(), false);
            }
        }
    }

    public void insertPieceIntoGrid(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for(GridElement block : blocks){
            block.makeOccupied();
            grid[block.getRow()][block.getCol()] = block;
        }
    }

    public boolean calculateLost() {
        for(GridElement g : grid[20]){
            if(g.isOccupied()){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all full rows in the playing-field
     * @return the amount of rows removed
     */
    public int removeFullRows() {
        ArrayList<Integer> fullRows = checkForFullRows();
        fullRows.sort(Comparator.reverseOrder());

        if(!fullRows.isEmpty()){
            for(Integer i : fullRows){
                removeRow(i);
            }
        }
        return fullRows.size();
    }

    /**
     * @return The row indices of the full rows
     */
    private ArrayList<Integer> checkForFullRows() {
        ArrayList<Integer> fullRows = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            int amountOccupied = 0;

            for (int j = 0; j <= 9; j++) {
                if(grid[i][j].isOccupied()){
                    amountOccupied++;
                }
            }
            if(amountOccupied == 10){
                fullRows.add(i);
            }
        }
        return fullRows;
    }

    /**
     * Copies the information from the row above, down to the row below, for each row above the given index.
     * @param rowIndex The row to be cleared
     */
    private void removeRow(int rowIndex) {
        for (int i = rowIndex; i <= 38; i++) {
            for (int j = 0; j <= 9; j++) {
                grid[i][j].setOccupied(grid[i+1][j].isOccupied());
                grid[i][j].setBackground(grid[i+1][j].getBackground());
            }
        }
    }

    public GridElement[][] getGrid() {
        return grid;
    }

    public int getRowDimensions() {
        return grid.length;
    }

    public int getColDimensions() {
        return grid[0].length;
    }

}
