package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class RotateCounterClockwiseCommand implements TetrisCommand {

    private final Game game;

    public RotateCounterClockwiseCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.rotateCounterClockwise();
    }
}
