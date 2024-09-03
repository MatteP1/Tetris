package org.tetris.tetrisGame.tetriminoFactory;

import org.tetris.Framework.Tetrimino;

public class SingleTypeTetriminoFactory implements TetriminoFactory {
    private StandardTetriminoFactory tetriminoFactory;
    private TetriminoType tetriminoType;

    public SingleTypeTetriminoFactory(TetriminoType type) {
        this.tetriminoType = type;
        this.tetriminoFactory = new StandardTetriminoFactory();
    }


    @Override
    public Tetrimino createNewTetrimino() {
        switch (tetriminoType) {
            case TYPE_I: return tetriminoFactory.createIPiece();
            case TYPE_J: return tetriminoFactory.createJPiece();
            case TYPE_L: return tetriminoFactory.createLPiece();
            case TYPE_O: return tetriminoFactory.createOPiece();
            case TYPE_S: return tetriminoFactory.createSPiece();
            case TYPE_T: return tetriminoFactory.createTPiece();
            case TYPE_Z: return tetriminoFactory.createZPiece();
            default: return tetriminoFactory.createIPiece();
        }
    }

    @Override
    public Tetrimino createNewInstanceOf(Tetrimino tetrimino) {
        return createNewTetrimino();
    }

    public enum TetriminoType {
        TYPE_I,
        TYPE_J,
        TYPE_L,
        TYPE_O,
        TYPE_S,
        TYPE_T,
        TYPE_Z
    }

}
