package player;

import field.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FinishHimShootStrategyTest {
    Player player;
    EnemyField enemyField;

    @Before
    public void setUp() throws Exception {
        enemyField = new EnemyField();
        player = new Player(new PlayersField(), enemyField);
        player.setShootBehavior(new FinishHimShootStrategy(enemyField));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shoot() {
        enemyField.update(new Point(5, 1), AbstractField.Answer.GET);
        enemyField.update(new Point(5, 3), AbstractField.Answer.GET);
        enemyField.update(new Point(5, 0), AbstractField.Answer.MISS);
        enemyField.update(new Point(4, 2), AbstractField.Answer.MISS);
        enemyField.update(new Point(5, 2), AbstractField.Answer.GET);
        Point shot = player.shoot();
        assertEquals(new Point(5, 4), shot);

        enemyField.update(new Point(0, 1), AbstractField.Answer.MISS);
        enemyField.update(new Point(0, 0), AbstractField.Answer.GET);
        shot = player.shoot();
        assertEquals(new Point(1, 0), shot);

        enemyField.update(new Point(8, 9), AbstractField.Answer.MISS);
        enemyField.update(new Point(9, 9), AbstractField.Answer.GET);
        shot = player.shoot();
        assertEquals(new Point(9, 8), shot);

        System.out.println(player.enemyField);
        System.out.println(shot);
    }
}