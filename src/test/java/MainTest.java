import tetris.tetrisGame.StandardGame;

public class MainTest {

    public static void main(String[] args) {
        StandardGame game = new StandardGame(new TestingGameFactory());
        game.startGame();
    }
}
