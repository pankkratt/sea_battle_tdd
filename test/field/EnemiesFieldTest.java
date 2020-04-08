package field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EnemiesFieldTest {
    EnemiesField field;

    @Before
    public void setUp() throws Exception {
        field = new EnemiesField();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUpdateWithGet() {
        field.update(new Point(3, 3), AbstractField.Answer.GET);
        Point[] ignored = new Point[] {
                new Point(3, 3),
                new Point(2, 2),
                new Point(2, 4),
                new Point(4, 2),
                new Point(4, 4),
        };
        Point[] noIgnored = new Point[] {
                new Point(3, 2),
                new Point(2, 3),
                new Point(4, 3),
                new Point(3, 4),
        };
        assertTrue(field.getIgnoredCells().containsAll(Arrays.asList(ignored)));
        assertFalse(field.getIgnoredCells().containsAll(Arrays.asList(noIgnored)));
    }

    @Test
    public void testUpdateWithSunk() {
        field.update(new Point(3, 3), AbstractField.Answer.SUNK);
        Point[] ignored = new Point[] {
                new Point(2, 2),
                new Point(2, 3),
                new Point(2, 4),
                new Point(3, 2),
                new Point(3, 3),
                new Point(3, 4),
                new Point(4, 2),
                new Point(4, 3),
                new Point(4, 4),
        };
        assertTrue(field.getIgnoredCells().containsAll(Arrays.asList(ignored)));
        field.update(new Point(5, 2), AbstractField.Answer.GET);
        field.update(new Point(5, 4), AbstractField.Answer.GET);
        field.update(new Point(5, 5), AbstractField.Answer.GET);
        field.update(new Point(5, 3), AbstractField.Answer.SUNK);
        for (int column = 4; column <= 6; column++) {
            for (int row = 1; row <= 6; row++) {
                assertTrue(field.getIgnoredCells().contains(new Point(column, row)));
            }
        }
    }
}