package player;

import field.EnemiesField;
import field.PlayersField;
import field.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class ShootStrategyTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player(new PlayersField(), new EnemiesField());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInputFromUserStrategy() throws UnsupportedEncodingException {
        String mockInput = "b3\nd6\ne7\ne11\n";
        InputStream mockInputStream = new ByteArrayInputStream(mockInput.getBytes(StandardCharsets.UTF_8.name()));
        player.shootBehavior = new InputFromUserStrategy(mockInputStream);
        assertEquals(new Point(1, 2), player.shoot());
        assertEquals(new Point(3, 5), player.shoot());
        assertEquals(new Point(4, 6), player.shoot());
        assertEquals(new Point(4, 10), player.shoot());
    }
}