package tetris.tetrisGame.commands;

import tetris.Framework.Game;

public class RotateClockwiseCommand implements TetrisCommand {

    private final Game game;

    public RotateClockwiseCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.rotateClockwise();
    }
}
