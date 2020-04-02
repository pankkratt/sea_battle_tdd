package game;

import field.Field;
import player.Computer;
import player.Human;
import player.Player;
import player.RandomArrangeShipsStrategy;

public class Game {
    private Player firstPlayer;
    private Player secondPlayer;
    private Field firstPlayerField;
    private Field firstPlayerEnemyField;
    private Field secondPlayerField;
    private Field secondPlayerEnemyField;
    private ShowBehavior showBehavior;

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
        showBehavior.show(firstPlayerField, firstPlayerEnemyField);
    }

    public void setShowBehavior(ShowBehavior showBehavior) {
        this.showBehavior = showBehavior;
    }
}
