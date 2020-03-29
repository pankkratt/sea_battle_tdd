package field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    Field field;
    Point point;

    @Before
    public void setUp() throws Exception {
        field = new Field();
        point = new Point(0, 0);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readFromCell() {
        assertEquals(field.readFromCell(point), Cell.Sign.EMPTY);
    }

    @Test
    public void writeInCell() {
        field.writeInCell(point, Cell.Sign.DESTROYED);
        assertEquals(field.readFromCell(point), Cell.Sign.DESTROYED);
    }

    @Test
    public void update() {
        Field.Answer answer;

        answer = field.update(point);
        assertEquals(answer, Field.Answer.MISS);
        assertEquals(field.readFromCell(point), Cell.Sign.MARKED);


        answer = field.update(point);
        assertEquals(answer, Field.Answer.REPEAT);

        field.writeInCell(point, Cell.Sign.DECK);
        answer = field.update(point);
        assertEquals(answer, Field.Answer.GET);
        assertEquals(field.readFromCell(point), Cell.Sign.DESTROYED);

        answer = field.update(point);
        assertEquals(answer, Field.Answer.REPEAT);

    }

    @Test
    public void testUpdate() {
        field.update(point, Field.Answer.SUNK);
        assertEquals(Cell.Sign.DESTROYED, field.readFromCell(point));

        field.update(new Point(1, 1), Field.Answer.GET);
        assertEquals(Cell.Sign.DESTROYED, field.readFromCell(new Point(1, 1)));

        field.update(new Point(2, 1), Field.Answer.MISS);
        assertEquals(Cell.Sign.MARKED, field.readFromCell(new Point(2, 1)));



    }
}