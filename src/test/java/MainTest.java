import tetris.tetrisGame.GUI;
import tetris.tetrisGame.TetrisLogger;
import tetris.tetrisGame.StandardGame;

public class MainTest {

    public static void main(String[] args) {
        StandardGame game = new StandardGame(new TestingGameFactory());
        GUI gui = new GUI(game);
        TetrisLogger logger = new TetrisLogger(game);
        game.startGame();
    }
}
