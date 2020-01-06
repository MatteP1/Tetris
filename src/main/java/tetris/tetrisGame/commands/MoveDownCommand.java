package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class MoveDownCommand implements TetrisCommand {

    private final Game game;

    public MoveDownCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.moveDown();
    }
}
