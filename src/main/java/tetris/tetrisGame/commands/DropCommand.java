package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class DropCommand implements TetrisCommand {

    private final Game game;

    public DropCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.drop();
    }
}
