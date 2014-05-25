package be.bendem.chess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;

@RunWith(JUnit4.class)
public class CoordinatesIteratorTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testCoordinatesIterator() {
        Assert.assertEquals(0, iteratorCount(board.iterator(new Coordinates(), Direction.Up)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Coordinates(), Direction.Left)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Coordinates(), Direction.LeftUp)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Coordinates(), Direction.RightUp)));
        Assert.assertEquals(0, iteratorCount(board.iterator(new Coordinates(), Direction.LeftDown)));

        Assert.assertEquals(7, iteratorCount(board.iterator(new Coordinates(), Direction.Down)));
        Assert.assertEquals(7, iteratorCount(board.iterator(new Coordinates(), Direction.Right)));
        Assert.assertEquals(7, iteratorCount(board.iterator(new Coordinates(), Direction.RightDown)));
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
