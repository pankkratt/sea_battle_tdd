import model.Cell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    Cell cell;

    @Before
    public void setUp() throws Exception {
        cell = new Cell(2, 4);

    }

    @Test
    public void testCheckCellsWritingSkills() {
        cell.setSign(Cell.Sign.DECK);
        assertEquals(Cell.Sign.DECK, cell.getSign());
    }

    @Test
    public void testCheckCellInitWithEmptySign() {
        assertEquals(Cell.Sign.EMPTY, cell.getSign());
    }
}