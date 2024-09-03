package org.tetris.tetrisGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.*;

import org.tetris.Framework.*;
import org.tetris.tetrisGame.commands.TetrisCommand;
import org.tetris.tetrisGame.factories.AbstractGameFactory;
import org.tetris.tetrisGame.factories.TetrisV1Factory;
import org.tetris.tetrisGame.gameOverStrategy.GameOverStrategy;
import org.tetris.tetrisGame.gameOverStrategy.LosingStrategy;
import org.tetris.tetrisGame.movementStrategy.MovementStrategy;
import org.tetris.tetrisGame.movementStrategy.StandardMovementStrategy;
import org.tetris.tetrisGame.rotationStrategy.MatrixStyleRotationStrategy;
import org.tetris.tetrisGame.rotationStrategy.RotationStrategy;
import org.tetris.tetrisGame.tetriminoFactory.StandardTetriminoFactory;
import org.tetris.tetrisGame.tetriminoFactory.TetriminoFactory;
import org.tetris.tetrisGame.validationStrategy.StandardValidationStrategy;
import org.tetris.tetrisGame.validationStrategy.ValidationStrategy;
import org.tetris.util.TetriminoCalculator;

/**
 * The game class containing most of the game handling logic, as well as keybindings.
 * The logic handling the movement of the tetriminos is defined in the abstract tetris.Framework.Tetrimino class and its sub-classes.
 */
public class StandardGame implements Game {

    // --------------------- FIELD VARIABLES ---------------------
    private int timePassed;
    private int score;
    private Timer time;
    private boolean paused;
    private boolean lost;

    /** Helper variable for moveDown method*/
    private int moveDownTries;

    /** The grid that the current tetrimino has to be placed in*/
    private PlayField playfield;

    private Tetrimino nextTetrimino;

    /** Variable containing the current tetris.Framework.Tetrimino in the playingfield*/
    private Tetrimino currentTetrimino;

    /** Variable containing the tetrimino available for swapping */
    private Tetrimino savedTetrimino;

    /** Variable telling if the swap feature has been used this round ("round" being the fall of the current tetris.Framework.Tetrimino) */
    private boolean changedCurrentTetriminoThisRound;

    /** Defines the amount of time that passes between each ingame timetick*/
    private int period;

    private RotationStrategy rotationStrategy;
    private MovementStrategy movementStrategy;
    private TetriminoFactory tetriminoFactory;
    private ValidationStrategy validationStrategy;
    private GameOverStrategy gameOverStrategy;
    private AbstractGameFactory gameFactory;
    private ArrayList<GameObserver> observers;


    // --------------------- GAME CREATION AND TIME HANDLING ---------------------
    /**
     * Creates a new game object with all stats set to 0 and a new, empty playingfield.
     */
    public StandardGame(AbstractGameFactory gameFactory) {
        timePassed = 0;
        score = 0;
        period = 1000*1;
        observers = new ArrayList<>();
        this.gameFactory = gameFactory;
        playfield = gameFactory.createPlayField();
        rotationStrategy = gameFactory.createRotationStrategy();
        movementStrategy = gameFactory.createMovementStrategy();
        tetriminoFactory = gameFactory.createTetriminoFactory();
        validationStrategy = gameFactory.createValidationStrategy();
        gameOverStrategy = gameFactory.createGameOverStrategy();
    }

    /**
     * Initializes the game time and requests the first piece
     */
    public void startGame(){
        paused = false;
        lost = false;
        nextTetrimino = generateRandomPiece();
        nextPiece();
        startTimer(period);
    }

    /**
     * Resets stats and playfield, and start a new game.
     */
    public void newGame(){
        stopGame();
        timePassed = 0;
        score = 0;
        lost = false;
        changedCurrentTetriminoThisRound = false;
        savedTetrimino = null;
        playfield.clearPlayField();
        startGame();
        paused = false;
        notifyObserversEntirePlayField();
    }

    /**
     * Stops the time.
     */
    private void stopGame(){
        time.cancel();
        paused = true;
    }

    /**
     * Creates a new timer and starts it.
     */
    private void resumeGame(){
        startTimer(period);
        paused = false;
    }

    /**
     * If game is running, the method pauses the game. Otherwise, it resumes it.
     */
    public void pauseResume(){
        if(paused){
            resumeGame();
        } else {
            stopGame();
        }
    }

    /**
     * Creates a new timer, and schedules it to run with a given delay and period
     *
     * @param period The time that goes between the execution of each run
     */
    public void startTimer(int period){
        paused = false;
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                step();
            }
        }, period, period);
    }

    /**
     * One ingame timeunit passes.
     */
    public void step(){
        timePassed++;
        computerMoveDown();
    }

    // --------------------- GAME LOGIC ---------------------

    /**
     * Calculates the next tetrimino to move down.
     */
    public void nextPiece() {
        currentTetrimino = nextTetrimino;
        nextTetrimino = generateRandomPiece();
        moveDownTries = 0; // The amount of times the player has tried to move it down unsuccessfully
    }

    private Tetrimino generateRandomPiece() {
        return tetriminoFactory.createNewTetrimino();
    }

    /**
     * This method is used to support the swap feature.
     * You can change your current tetrimino with one you have set a side earlier
     * If you haven't set one aside earlier (first time the method is ever called), you set your piece aside, and the next piece is generated.
     */
    public void changeCurrentTetrimino(){
        if(!changedCurrentTetriminoThisRound) {
            if (savedTetrimino == null) {
                savedTetrimino = currentTetrimino;
                savedTetrimino = tetriminoFactory.createNewInstanceOf(savedTetrimino);
                nextPiece();
            } else {
                Tetrimino temp = savedTetrimino;
                savedTetrimino = currentTetrimino;
                savedTetrimino = tetriminoFactory.createNewInstanceOf(savedTetrimino);
                currentTetrimino = temp;
            }
            changedCurrentTetriminoThisRound = true;
        }
        notifyObservers(savedTetrimino.getBlocks());
        notifyObservers(currentTetrimino.getBlocks());
    }

    /**
     * Makes the current tetrimino move down 1 row if there is space for it.
     * If there isn't space for it. Then it needs to be inserted into the playingfield, and a new piece should be created.
     */
    public void computerMoveDown() {
        Map<GridElement, Position> suggestedMove = movementStrategy.moveDown(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful){
            moveCurrentTetrimino(suggestedMove, MovementType.MOVE_DOWN);
            moveDownTries = 0;
            notifyObservers(currentTetrimino.getBlocks());
        } else {
            insertIntoGrid();
        }
    }

    /**
     * Helper method for the computerMoveDownMethod.
     * Inserts the current piece into the grid and checks to see if the game is lost.
     */
    private synchronized void insertIntoGrid() {
        playfield.insertPieceIntoPlayField(currentTetrimino);

        // After the moveDown, check if any rows have been filled out.
        List<Integer> fullRows = playfield.removeFullRows();
        increaseScore(fullRows.size());
        notifyObserversRowsChanges(fullRows);
        boolean lost = gameOverStrategy.calculateGameOver(playfield);
        if(!lost){
            nextPiece();
            changedCurrentTetriminoThisRound = false;
            notifyObservers(currentTetrimino.getBlocks());
        } else {
            this.lost = true;
            stopGame();
            notifyObserversEntirePlayField();
        }
    }

    /**
     * Method to drop the current piece to the bottom of the playingfield.
     */
    @Override
    public void drop() {
        Map<GridElement, Position> suggestedMove = movementStrategy.drop(playfield, currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.DROP);
            insertIntoGrid();
        }
    }

    /**
     * Move down the current tetrimino 1 gridelement
     */
    @Override
    public void moveDown(){
        time.cancel();
        Map<GridElement, Position> suggestedMove = movementStrategy.moveDown(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.MOVE_DOWN);
        }
        if(!successful){
            moveDownTries++;
        }
        if(moveDownTries > 10){
            computerMoveDown();
        }
        startTimer(period);
    }

    /**
     * Move the tetrimino left
     */
    @Override
    public void moveLeft() {
        Map<GridElement, Position> suggestedMove = movementStrategy.moveLeft(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.MOVE_LEFT);
        }
    }

    /**
     * Move the tetrimino right
     */
    @Override
    public void moveRight() {
        Map<GridElement, Position> suggestedMove = movementStrategy.moveRight(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.MOVE_RIGHT);
        }
    }

    /**
     * Rotate the current tetrimino in the clockwise direction
     */
    @Override
    public void rotateClockwise() {
        Map<GridElement, Position> suggestedMove = rotationStrategy.rotateClockWise(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.ROTATE_CW);
        }
    }

    /**
     * Rotate the current tetrimino in the counter-clockwise direction
     */
    @Override
    public void rotateCounterClockwise() {
        Map<GridElement, Position> suggestedMove = rotationStrategy.rotateCounterClockWise(currentTetrimino);
        boolean successful = validationStrategy.validateMove(new ArrayList<>(suggestedMove.values()), playfield);
        if(successful) {
            moveCurrentTetrimino(suggestedMove, MovementType.ROTATE_CCW);
        }
    }

    private void moveCurrentTetrimino(Map<GridElement, Position> wantedMove, MovementType movementType) {
        currentTetrimino.applyMovement(wantedMove, movementType);
        notifyObservers(currentTetrimino.getBlocks());
    }

    // --------------------- SETTERS HANDLER ---------------------

    /**
     * Increases the current score with the specified value
     * @param amount Amount to be added to the score
     */
    public void increaseScore(int amount){
        score += amount;
    }

    // --------------------- GETTERS ---------------------

    public int getTimePassed(){
        return timePassed;
    }

    public int getScore(){
        return score;
    }

    public boolean isPaused(){
        return paused;
    }

    @Override
    public Tetrimino getSavedTetrimino(){
        return savedTetrimino;
    }

    @Override
    public Tetrimino getCurrentTetrimino() {
        return currentTetrimino;
    }

    @Override
    public PlayField getPlayField() {
        return playfield;
    }

    @Override
    public Tetrimino getNextTetrimino(){
        return nextTetrimino;
    }

    public boolean hasLost(){
        return lost;
    }

    public AbstractGameFactory getGameFactory() {
        return gameFactory;
    }

    public void setGameFactory(AbstractGameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    // --------------------- OBSERVER ---------------------

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(ArrayList<GridElement> gridElements) {
        for (GameObserver observer : observers) {
            observer.playFieldChangedAt(gridElements);
        }
    }

    public void notifyObserversEntirePlayField() {
        ArrayList<GridElement> allGridElements = new ArrayList<>();
        Iterable<Iterable<GridElement>> grid = playfield.getGrid();
        for (Iterable<GridElement> i : grid) {
            for (GridElement g : i) {
                allGridElements.add(g);
            }
        }
        notifyObservers(allGridElements);
    }

    public void notifyObserversRowsChanges(List<Integer> rows) {
        ArrayList<GridElement> gridElements = new ArrayList<>();
        for (Integer i : rows) {
            playfield.getBlocksInRow(i).iterator().forEachRemaining(gridElements::add);
        }
        notifyObservers(gridElements);
    }

}
