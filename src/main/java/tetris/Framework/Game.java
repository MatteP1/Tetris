package tetris.Framework;

public interface Game {
    PlayField getPlayField();
    Tetrimino getNextTetrimino();
    Tetrimino getCurrentTetrimino();
    Tetrimino getSavedTetrimino();
    void moveDown();
    void moveLeft();
    void moveRight();
    void rotateClockwise();
    void rotateCounterClockwise();
    void drop();
}
