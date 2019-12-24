package tetris.Framework;

public interface Game {
    Tetrimino getNextTetrimino();
    Tetrimino getCurrentTetrimino();
    Tetrimino getSavedTetrimino();

}
