package tetris.Framework;

import tetris.tetrisGame.GridElement;

public interface PlayField {
    void clearPlayField();
    void insertPieceIntoPlayField(Tetrimino tetrimino);
    int removeFullRows();
    Iterable<GridElement> getBlocksInRow(int rowNumber);
    Iterable<GridElement> getBlocksInCol(int colNumber);
    Iterable<Iterable<GridElement>> getGrid();
    int getRowDimensions();
    int getColDimensions();


}
