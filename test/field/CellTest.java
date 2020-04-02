package field;

import field.Cell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCell() {
        Cell cell = new Cell();
        assertEquals(cell.getSign(), Cell.Sign.EMPTY);
        cell.setSign(Cell.Sign.DECK);
        assertEquals(cell.getSign(), Cell.Sign.DECK);
    }
}