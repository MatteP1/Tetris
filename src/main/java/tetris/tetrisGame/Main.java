package tetris.tetrisGame;

import tetris.tetrisGame.factories.ModernTetrisFactory;
import tetris.tetrisGame.factories.TetrisV1Factory;

public class Main {
    // --------------------- MAIN METHOD ---------------------
    public static void main(String[] args) {
        StandardGame game = new StandardGame(new TetrisV1Factory());
        game.startGame();
    }
}
