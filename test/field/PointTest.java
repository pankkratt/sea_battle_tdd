package field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPoint() {
        int x = 56;
        int y = 7;
        Point point = new Point(x, y);
        int column = point.getColumn();
        int row = point.getRow();
        assertEquals(x, column);
        assertEquals(y, row);
    }
}