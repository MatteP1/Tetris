package org.tetris;
import org.tetris.tetrisGame.GUI;
import org.tetris.tetrisGame.StandardGame;
import org.tetris.tetrisGame.TetrisLogger;

public class MainTest {

    public static void main(String[] args) {
        StandardGame game = new StandardGame(new TestingGameFactory());
        GUI gui = new GUI(game);
        TetrisLogger logger = new TetrisLogger(game);
        game.startGame();
    }
}
