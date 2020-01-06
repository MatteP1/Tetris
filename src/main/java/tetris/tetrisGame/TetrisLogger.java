package tetris.tetrisGame;

import tetris.Framework.Game;
import tetris.Framework.GameObserver;
import tetris.Framework.Tetrimino;

import java.util.List;

public class TetrisLogger implements GameObserver {

    private StandardGame game;
    private int timePassed;
    private Tetrimino currentTetrimino;
    private String divider = "----------------";

    public TetrisLogger(StandardGame game) {
        game.addObserver(this);
        this.game = game;
        currentTetrimino = game.getCurrentTetrimino();
    }

    @Override
    public void playFieldChangedAt(List<GridElement> grids) {
        if(timePassed != game.getTimePassed()) {
            timePassed = game.getTimePassed();
            System.out.println("Time passed: " + timePassed);
            for(GridElement g : game.getCurrentTetrimino().getBlocks()){
                System.out.print("(" + g.getRow() +", "+  g.getCol() + ") ");
            }
            System.out.println();
            System.out.println(divider);
        }
        if(currentTetrimino == null) {
            currentTetrimino = game.getCurrentTetrimino();
        } else if(currentTetrimino != game.getCurrentTetrimino()) {
            currentTetrimino = game.getCurrentTetrimino();
            System.out.println("Piece fallen");
            System.out.println("Next piece is: " + game.getCurrentTetrimino().toString());
            System.out.println("Currently occupied gridElements:");
            for(Iterable<GridElement> G : game.getPlayField().getGrid()){
                for(GridElement g : G){
                    if(g.isOccupied()){
                        System.out.print("(" + g.getRow() +", "+  g.getCol() + ") ");
                    }
                }
            }
            System.out.println();
            System.out.println(divider);
        }
        if(game.hasLost()) {
            System.out.println("Game Over!");
            System.out.println(divider);
        }

    }
}
