import org.junit.Before;
import org.junit.Test;
import tetris.Framework.Tetrimino;
import tetris.tetrisGame.tetriminoFactory.SingleTypeTetriminoFactory;
import tetris.util.TetriminoCalculator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class testUtilTetriminoCalculator {

    private Tetrimino iPiece;
    private Tetrimino lPiece;
    private Tetrimino oPiece;

    @Before
    public void setup() {
        iPiece = (new SingleTypeTetriminoFactory(
                SingleTypeTetriminoFactory.TetriminoType.TYPE_I))
                .createNewTetrimino();
        lPiece = (new SingleTypeTetriminoFactory(
                SingleTypeTetriminoFactory.TetriminoType.TYPE_L))
                .createNewTetrimino();
        oPiece = (new SingleTypeTetriminoFactory(
                SingleTypeTetriminoFactory.TetriminoType.TYPE_O))
                .createNewTetrimino();

    }

    @Test
    public void testFindEnclosingRectangleSize() {
        int result = TetriminoCalculator.findEnclosingRectangleSize(iPiece);
        assertThat(result, is(4));

        int result2 = TetriminoCalculator.findEnclosingRectangleSize(lPiece);
        assertThat(result2, is(3));

        int result3 = TetriminoCalculator.findEnclosingRectangleSize(oPiece);
        assertThat(result3, is(2));

    }

}
