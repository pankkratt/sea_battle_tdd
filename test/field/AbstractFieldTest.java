package field;

import game.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractFieldTest {
    private AbstractField enemiesField;
    private AbstractField playersField;

    @Before
    public void setUp() throws Exception {
        playersField = new PlayersField();
        enemiesField = new EnemiesField();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void theFieldIsInitializedByEmptyCells() {
        for (AbstractField field : new AbstractField[]{playersField, enemiesField}) {
            for (int column = 0; column < Configuration.getFieldWidth(); column++) {
                for (int row = 0; row < Configuration.getFieldHeight(); row++) {
                    assertEquals(Cell.Sign.EMPTY, field.readFromCell(new Point(column, row)));
                }
            }
        }
    }
}