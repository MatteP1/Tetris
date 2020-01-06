package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class MoveRightCommand implements TetrisCommand {

    private final Game game;

    public MoveRightCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.moveRight();
    }
}
