package tetris.tetrisGame.commands;

import tetris.Framework.Game;
import tetris.tetrisGame.StandardGame;

public class ChangePieceCommand implements TetrisCommand{
    private final Game game;

    public ChangePieceCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        if(game instanceof StandardGame) {
            ((StandardGame) game).changeCurrentTetrimino();
        }
    }
}
