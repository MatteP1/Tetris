package org.tetris.tetrisGame.commands;

import org.tetris.Framework.Game;

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
