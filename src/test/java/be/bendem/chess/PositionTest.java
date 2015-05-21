package be.bendem.chess;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PositionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructor() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new Position(-1, 0);
    }

    @Test
    public void testAdd() throws Exception {
        Position position = new Position();

        position.setX(2);
        position.setY(2);
        position.add(Direction.Up);
        Assert.assertEquals(2, position.getX());
        Assert.assertEquals(1, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.Down);
        Assert.assertEquals(2, position.getX());
        Assert.assertEquals(3, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.Left);
        Assert.assertEquals(1, position.getX());
        Assert.assertEquals(2, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.Right);
        Assert.assertEquals(3, position.getX());
        Assert.assertEquals(2, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.LeftUp);
        Assert.assertEquals(1, position.getX());
        Assert.assertEquals(1, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.LeftDown);
        Assert.assertEquals(1, position.getX());
        Assert.assertEquals(3, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.RightUp);
        Assert.assertEquals(3, position.getX());
        Assert.assertEquals(1, position.getY());

        position.setX(2);
        position.setY(2);
        position.add(Direction.RightDown);
        Assert.assertEquals(3, position.getX());
        Assert.assertEquals(3, position.getY());

        exception.expect(IllegalArgumentException.class);
        position.setY(0);
        position.add(Direction.Up);
    }

    @Test
    public void testOverflow() throws Exception {
        Assert.assertTrue(Position.overflow(-1, -1));
        Assert.assertTrue(Position.overflow(-1, 0));
        Assert.assertTrue(Position.overflow(0, -1));
        Assert.assertTrue(Position.overflow(8, 8));
        Assert.assertTrue(Position.overflow(8, 0));
        Assert.assertTrue(Position.overflow(0, 8));

        Assert.assertFalse(Position.overflow(0, 0));
        Assert.assertFalse(Position.overflow(2, 3));
        Assert.assertFalse(Position.overflow(7, 7));

        Assert.assertTrue(Position.overflow(new Position(), Direction.Up));
        Assert.assertFalse(Position.overflow(new Position(), Direction.Down));
    }

}
