package field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShipTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShip() {
        for (int i = 0; i < 1000; i++) {
            Ship ship = new Ship(4);
            List<Point> points = ship.getPoints();
            assertEquals(points.size(), 4);
            for (Point point : points) {
                int column = point.getColumn();
                int row = point.getRow();
                assertTrue(column >= 0);
                assertTrue(column < Field.WIDTH);
                assertTrue(row >= 0);
                assertTrue(row < Field.HEIGHT);
            }
        }
    }
}