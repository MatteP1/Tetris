package tetris.Framework;

import tetris.tetrisGame.GridElement;

import java.util.List;

public interface PlayField {
    void clearPlayField();
    void insertPieceIntoPlayField(Tetrimino tetrimino);
    List<Integer> removeFullRows();
    Iterable<GridElement> getBlocksInRow(int rowNumber);
    Iterable<GridElement> getBlocksInCol(int colNumber);
    Iterable<Iterable<GridElement>> getGrid();
    int getRowDimensions();
    int getColDimensions();


}
