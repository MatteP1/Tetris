package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class MoveLeftCommand implements TetrisCommand {

    private final Game game;

    public MoveLeftCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.moveLeft();
    }
}
