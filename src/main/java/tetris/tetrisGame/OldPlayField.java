package tetris.tetrisGame;

import tetris.Framework.PlayField;
import tetris.Framework.Tetrimino;

import java.util.*;
import java.awt.Color;

/**
 * Class representing the PlayField.
 * @author MatRusTy
 */
public class OldPlayField implements PlayField {
    private GridElement[][] grid;


    public OldPlayField() {
        grid = new GridElement[40][10];
        clearPlayField();
    }

    /**
     * Sets up the grid with empty GridElements
     */
    @Override
    public void clearPlayField() {
        for (int i = 0; i <= 39; i++) {
            for (int j = 0; j <= 9; j++) {
                grid[i][j] = new GridElement(i, j, Color.GRAY.darker(), false);
            }
        }
    }

    @Override
    public void insertPieceIntoPlayField(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for(GridElement block : blocks){
            block.makeOccupied();
            grid[block.getRow()][block.getCol()] = block;
        }
    }

    /**
     * Removes all full rows in the playing-field
     * @return the amount of rows removed
     */
    @Override
    public List<Integer> removeFullRows() {
        ArrayList<Integer> fullRows = checkForFullRows();
        fullRows.sort(Comparator.reverseOrder());

        if(!fullRows.isEmpty()){
            for(Integer i : fullRows){
                removeRow(i);
            }
        }
        return fullRows;
    }

    @Override
    public Iterable<GridElement> getBlocksInRow(int rowNumber) {
        return Arrays.asList(grid[rowNumber]);
    }

    @Override
    public Iterable<GridElement> getBlocksInCol(int colNumber) {
        ArrayList<GridElement> result = new ArrayList<>(40);
        for (int i = 0; i <= 39; i++) {
            result.add(grid[i][colNumber]);
        }
        return result;
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

    public Iterable<Iterable<GridElement>> getGrid() {
        ArrayList<Iterable<GridElement>> result = new ArrayList<>(40);
        for (int i = 0; i <= 39; i++) {
            Iterable<GridElement> it = Arrays.asList(grid[i]);
            result.add(it);
        }
        return result;
    }

    @Override
    public int getRowDimensions() {
        return grid.length;
    }

    @Override
    public int getColDimensions() {
        return grid[0].length;
    }

}
