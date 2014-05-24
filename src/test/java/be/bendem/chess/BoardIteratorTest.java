package be.bendem.chess;

import be.bendem.chess.filter.ColorFilter;
import be.bendem.chess.filter.PieceFilter;
import be.bendem.chess.pieces.AbstractPiece;
import junit.framework.Assert;
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
        Iterator<AbstractPiece> pieceIterator = board.iterator();
        int count = 0;
        int countNull = 0;
        while(pieceIterator.hasNext()) {
            ++count;
            if(pieceIterator.next() == null) {
                ++countNull;
            }
        }

        Assert.assertEquals(8*8, count);
        Assert.assertEquals(8*4, countNull);
    }

    @Test
    public void testBoardIteratorColorFilter() throws Exception {
        for(Color color : Color.values()) {
            Iterator<AbstractPiece> pieceIterator = board.iterator(new ColorFilter(color));
            int count = 0;
            while(pieceIterator.hasNext()) {
                ++count;
                AbstractPiece piece = pieceIterator.next();
                Assert.assertNotNull(piece);
                Assert.assertTrue(piece.getColor() == color);
            }

            Assert.assertEquals(16, count);
        }
    }

    @Test
    public void testBoardIteratorPieceFilter() throws Exception {
        Iterator<AbstractPiece> pieceIterator = board.iterator(new PieceFilter());
        int count = 0;
        while(pieceIterator.hasNext()) {
            ++count;
            Assert.assertNotNull(pieceIterator.next());
        }
        Assert.assertEquals(32, count);
    }

}
