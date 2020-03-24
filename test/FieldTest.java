import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    Field field;

    @org.junit.Before
    public void setUp() throws Exception {
        field = new Field();
        field.init();
    }

    @Test
    public void testInitFieldCellIsEmpty() {
        assertEquals(Cell.Sign.EMPTY, field.readInCell(2, 4));
    }

    @Test
    public void testWriteInCell() {
        field.writeInCell(3, 7, Cell.Sign.DECK);
        assertEquals(Cell.Sign.DECK, field.readInCell(3, 7));
    }
}