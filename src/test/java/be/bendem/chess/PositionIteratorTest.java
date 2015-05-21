package be.bendem.chess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

@RunWith(JUnit4.class)
public class PositionIteratorTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testCoordinatesIterator() {
        Assert.assertEquals(0, iteratorCount(board.iterator(new Position(), Direction.Up)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Position(), Direction.Left)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Position(), Direction.LeftUp)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Position(), Direction.RightUp)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Position(), Direction.LeftDown)));

        Assert.assertEquals(7, iteratorCount(board.iterator(new Position(), Direction.Down)));
        Assert.assertEquals(7, iteratorCount(board.iterator(new Position(), Direction.Right)));
        Assert.assertEquals(7, iteratorCount(board.iterator(new Position(), Direction.RightDown)));
    }

    private int iteratorCount(Iterator<?> it) {
        int count = 0;
        while(it.hasNext()) {
            it.next();
            ++count;
        }
        return count;
    }

}
