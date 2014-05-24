package be.bendem.chess;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CoordinatesTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructor() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new Coordinates(-1, 0);
    }

    @Test
    public void testAdd() throws Exception {
        Coordinates coordinates = new Coordinates(0, 0);

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.Up);
        Assert.assertEquals(2, coordinates.getX());
        Assert.assertEquals(1, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.Down);
        Assert.assertEquals(2, coordinates.getX());
        Assert.assertEquals(3, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.Left);
        Assert.assertEquals(1, coordinates.getX());
        Assert.assertEquals(2, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.Right);
        Assert.assertEquals(3, coordinates.getX());
        Assert.assertEquals(2, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.LeftUp);
        Assert.assertEquals(1, coordinates.getX());
        Assert.assertEquals(1, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.LeftDown);
        Assert.assertEquals(1, coordinates.getX());
        Assert.assertEquals(3, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.RightUp);
        Assert.assertEquals(3, coordinates.getX());
        Assert.assertEquals(1, coordinates.getY());

        coordinates.setX(2);
        coordinates.setY(2);
        coordinates.add(Direction.RightDown);
        Assert.assertEquals(3, coordinates.getX());
        Assert.assertEquals(3, coordinates.getY());

        exception.expect(IllegalArgumentException.class);
        coordinates.setY(0);
        coordinates.add(Direction.Up);
    }

    @Test
    public void testOverflow() throws Exception {
        Assert.assertTrue(Coordinates.overflow(-1, -1));
        Assert.assertTrue(Coordinates.overflow(-1, 0));
        Assert.assertTrue(Coordinates.overflow(0, -1));
        Assert.assertTrue(Coordinates.overflow(8, 8));
        Assert.assertTrue(Coordinates.overflow(8, 0));
        Assert.assertTrue(Coordinates.overflow(0, 8));

        Assert.assertFalse(Coordinates.overflow(0, 0));
        Assert.assertFalse(Coordinates.overflow(2, 3));
        Assert.assertFalse(Coordinates.overflow(7, 7));

        Assert.assertTrue(Coordinates.overflow(new Coordinates(0, 0), Direction.Up));
        Assert.assertFalse(Coordinates.overflow(new Coordinates(0, 0), Direction.Down));
    }

}
