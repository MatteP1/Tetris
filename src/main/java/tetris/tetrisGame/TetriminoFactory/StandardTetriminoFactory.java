package tetris.tetrisGame.TetriminoFactory;

import tetris.Framework.Tetrimino;
import tetris.tetrisGame.GridElement;
import tetris.tetrisGame.StandardTetrimino;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StandardTetriminoFactory implements TetriminoFactory {
    private Random random;

    public StandardTetriminoFactory() {
        random = new Random();
    }

    @Override
    public Tetrimino createNewTetrimino() {
        int nextPiece = random.nextInt(7)+1;
        switch (nextPiece){
            case 1 : return createIPiece();
            case 2 : return createJPiece();
            case 3 : return createLPiece();
            case 4 : return createOPiece();
            case 5 : return createSPiece();
            case 6 : return createTPiece();
            case 7 : return createZPiece();
            default: return createIPiece();
        }
    }

    @Override
    public Tetrimino createNewInstanceOf(Tetrimino tetrimino) {
        switch (tetrimino.getType()) {
            case "I" : return createIPiece();
            case "J" : return createJPiece();
            case "L" : return createLPiece();
            case "O" : return createOPiece();
            case "S" : return createSPiece();
            case "T" : return createTPiece();
            case "Z" : return createZPiece();
            default: return createIPiece();
        }
    }

    public Tetrimino createIPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,3, Color.CYAN, true));
        blocks.add(new GridElement(20,4, Color.CYAN, true));
        blocks.add(new GridElement(20,5, Color.CYAN, true));
        blocks.add(new GridElement(20,6, Color.CYAN, true));
        return new StandardTetrimino("I", blocks, Color.CYAN, 4);
    }

    public Tetrimino createJPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(21,3, Color.BLUE, true));
        blocks.add(new GridElement(20,3, Color.BLUE, true));
        blocks.add(new GridElement(20,4, Color.BLUE, true));
        blocks.add(new GridElement(20,5, Color.BLUE, true));
        return new StandardTetrimino("J", blocks, Color.CYAN, 4);
    }

    public Tetrimino createLPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,3, Color.ORANGE, true));
        blocks.add(new GridElement(20,4, Color.ORANGE, true));
        blocks.add(new GridElement(20,5, Color.ORANGE, true));
        blocks.add(new GridElement(21,5, Color.ORANGE, true));
        return new StandardTetrimino("L", blocks, Color.CYAN, 4);
    }

    public Tetrimino createOPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,4, Color.YELLOW, true));
        blocks.add(new GridElement(21,4, Color.YELLOW, true));
        blocks.add(new GridElement(20,5, Color.YELLOW, true));
        blocks.add(new GridElement(21,5, Color.YELLOW, true));
        return new StandardTetrimino("O", blocks, Color.CYAN, 4);
    }

    public Tetrimino createSPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,3, Color.GREEN, true));
        blocks.add(new GridElement(20,4, Color.GREEN, true));
        blocks.add(new GridElement(21,4, Color.GREEN, true));
        blocks.add(new GridElement(21,5, Color.GREEN, true));
        return new StandardTetrimino("S", blocks, Color.CYAN, 4);
    }

    public Tetrimino createTPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(20,3, Color.MAGENTA, true));
        blocks.add(new GridElement(20,4, Color.MAGENTA, true));
        blocks.add(new GridElement(21,4, Color.MAGENTA, true));
        blocks.add(new GridElement(20,5, Color.MAGENTA, true));
        return new StandardTetrimino("T", blocks, Color.CYAN, 4);
    }

    public Tetrimino createZPiece() {
        ArrayList<GridElement> blocks = new ArrayList<>();
        blocks.add(new GridElement(21,3, Color.RED, true));
        blocks.add(new GridElement(21,4, Color.RED, true));
        blocks.add(new GridElement(20,4, Color.RED, true));
        blocks.add(new GridElement(20,5, Color.RED, true));
        return new StandardTetrimino("Z", blocks, Color.CYAN, 4);
    }

}
