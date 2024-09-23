package org.tetris.tetrisGame;

import org.tetris.tetrisGame.factories.*;

/**
 * Entry-point of Tetris!
 *
 * @author Mathias Pedersen
 */

public class Main {
    // --------------------- MAIN METHOD ---------------------
    public static void main(String[] args) {
        StandardGame game = new StandardGame(new TetrisV1Factory());
        new GUI(game);
        game.addObserver(new GameScoreSubmitService(game, System.getProperty("user.name")));
        game.startGame();
    }
}
