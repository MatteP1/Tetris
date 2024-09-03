package org.tetris.tetrisGame.commands;

import org.tetris.Framework.Game;

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
