package be.bendem.chess.pieces;

import be.bendem.chess.Board;
import be.bendem.chess.Color;
import be.bendem.chess.Direction;
import be.bendem.chess.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PawnTest {

    private Board board = new Board();
    private Pawn pawn;

    @Before
    public void setUp() throws Exception {
        pawn = new Pawn(Color.Black, Direction.Down, new Position(0, 1));
    }

    @Test
    public void testCanMove() throws Exception {
        Assert.assertFalse(pawn.canMove(board, board.createMove(pawn, Direction.Up)));
        Assert.assertTrue(pawn.canMove(board, board.createMove(pawn, Direction.Down)));
    }

    @Test
    public void testGetAllDirections() throws Exception {
        pawn.getAllDirections().forEach(
            direction ->
                Assert.assertTrue(direction.getY() > 0)
        );
    }

    @Test
    public void testIsMoveCountRestricted() throws Exception {
        Assert.assertFalse(pawn.isMoveCountRestricted());

        pawn.move(new Position(0, 2));
        Assert.assertTrue(pawn.isMoveCountRestricted());
    }

}
