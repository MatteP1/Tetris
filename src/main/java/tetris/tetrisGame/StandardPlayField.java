package tetris.tetrisGame;

import tetris.Framework.PlayField;
import tetris.Framework.Position;
import tetris.Framework.Tetrimino;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;

public class StandardPlayField implements PlayField {

    HashMap<Position, GridElement> grid;
    int rows;
    int cols;

//    public StandardPlayField(int rows, int cols) {
//        grid = new HashMap<>(rows * cols);
//        this.rows = rows+1;
//        this.cols = cols;
//        clearPlayField();
//    }

    public StandardPlayField() {
        this.rows = 40+1;
        this.cols = 10;
        grid = new HashMap<>(rows * cols);
        clearPlayField();
    }


    @Override
    public void clearPlayField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid.put(new Position(i, j), new GridElement(i, j, Color.GRAY.darker(), false));
            }
        }
    }

    @Override
    public void insertPieceIntoPlayField(Tetrimino tetrimino) {
        ArrayList<GridElement> blocks = tetrimino.getBlocks();
        for(GridElement block : blocks){
            GridElement g = grid.get(new Position(block.getRow(), block.getCol()));
            g.makeOccupied();
            g.setBackground(block.getBackground());
        }
    }

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

    private ArrayList<Integer> checkForFullRows() {
        ArrayList<Integer> fullRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            int amountOccupied = 0;

            for (int j = 0; j < cols; j++) {
                if(grid.get(new Position(i,j)).isOccupied()){
                    amountOccupied++;
                }
            }
            if(amountOccupied == 10) {
                fullRows.add(i);
            }
        }
        return fullRows;
    }

    private void removeRow(int rowIndex) {
        for (int i = rowIndex; i < rows - 1; i++) {
            for (int j = 0; j < cols; j++) {
                grid.get(new Position(i, j)).setOccupied(grid.get(new Position(i+1, j)).isOccupied());
                grid.get(new Position(i, j)).setBackground(grid.get(new Position(i+1, j)).getBackground());
            }
        }
    }

    @Override
    public Iterable<GridElement> getBlocksInRow(int rowNumber) {
        ArrayList<GridElement> blocks = new ArrayList<>(cols);
        for (int i = 0; i < cols; i++) {
            blocks.add(grid.get(new Position(rowNumber, i)));
        }
        return blocks;
    }

    @Override
    public Iterable<GridElement> getBlocksInCol(int colNumber) {
        ArrayList<GridElement> blocks = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            blocks.add(grid.get(new Position(i, colNumber)));
        }
        return blocks;
    }

    @Override
    public Iterable<Iterable<GridElement>> getGrid() {
        ArrayList<Iterable<GridElement>> result = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            ArrayList<GridElement> rowBlocks = new ArrayList<>(cols);
            for (int j = 0; j < cols; j++) {
                rowBlocks.add(grid.get(new Position(i, j)));
            }
            result.add(rowBlocks);
        }
        return result;
    }

    @Override
    public int getRowDimensions() {
        return rows;
    }

    @Override
    public int getColDimensions() {
        return cols;
    }
}
