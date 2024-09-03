package org.tetris.Framework;

import java.util.List;

import org.tetris.tetrisGame.GridElement;

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
