package be.bendem.chess;

import be.bendem.chess.filter.ColorPredicate;
import be.bendem.chess.filter.PiecePredicate;
import be.bendem.chess.pieces.Piece;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

@RunWith(JUnit4.class)
public class BoardIteratorTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testBoardIterator() throws Exception {
        Iterator<Piece> pieceIterator = board.iterator();
        int count = 0;
        int countNull = 0;
        while(pieceIterator.hasNext()) {
            ++count;
            if(pieceIterator.next() == null) {
                ++countNull;
            }
        }

        Assert.assertEquals(Board.WIDTH * Board.HEIGHT, count);
        Assert.assertEquals(Board.WIDTH * 4, countNull);
    }

    @Test
    public void testBoardIteratorColorFilter() throws Exception {
        for(Color color : Color.values()) {
            Iterator<Piece> pieceIterator = board.iterator(new ColorPredicate(color));
            int count = 0;
            while(pieceIterator.hasNext()) {
                ++count;
                Piece piece = pieceIterator.next();
                Assert.assertNotNull(piece);
                Assert.assertTrue(piece.getColor() == color);
            }

            Assert.assertEquals(Board.WIDTH * 2, count);
        }
    }

    @Test
    public void testBoardIteratorPieceFilter() throws Exception {
        Iterator<Piece> pieceIterator = board.iterator(new PiecePredicate());
        int count = 0;
        while(pieceIterator.hasNext()) {
            ++count;
            Assert.assertNotNull(pieceIterator.next());
        }
        Assert.assertEquals(Board.WIDTH * 4, count);
    }

}
