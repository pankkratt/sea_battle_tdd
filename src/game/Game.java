package game;

import field.Field;
import player.Computer;
import player.Human;
import player.Player;
import player.RandomArrangeShipsStrategy;

public class Game {
    public static final int MAX_NUM_DECKS = 4;
    private Player firstPlayer;
    private Player secondPlayer;
    private Field firstPlayerField;
    private Field firstPlayerEnemyField;
    private Field secondPlayerField;
    private Field secondPlayerEnemyField;
    private Viewable viewable;

    public Game() {
        firstPlayerField = new Field();
        firstPlayerEnemyField = new Field();
        secondPlayerField = new Field();
        secondPlayerEnemyField = new Field();
        firstPlayer = new Human(firstPlayerField, firstPlayerEnemyField);
        secondPlayer = new Computer(secondPlayerField, secondPlayerEnemyField);
    }

    public void init() {
        firstPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        firstPlayer.arrangeShips();
    }

    public void play() {
        show();
    }

    public void show() {
        viewable.show(firstPlayerField, firstPlayerEnemyField);
    }

    public void setViewable(Viewable viewable) {
        this.viewable = viewable;
    }
}
