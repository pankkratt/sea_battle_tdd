package game;

import field.Field;
import field.Point;
import player.*;

public class Game {
    public static final int MAX_NUM_DECKS = 4;
    private Player firstPlayer;
    private Player secondPlayer;
    private Field firstPlayerField;
    private Field firstPlayerEnemyField;
    private Field secondPlayerField;
    private Field secondPlayerEnemyField;
    private Status status;
    private Viewable viewable;
    private int numberOfDecks;
    private enum Status {
        PLAY,
        FIRST_WIN,
        SECOND_WIN,
    }

    public Game() {
        firstPlayerField = new Field();
        firstPlayerEnemyField = new Field();
        secondPlayerField = new Field();
        secondPlayerEnemyField = new Field();
        firstPlayer = new Human(firstPlayerField, firstPlayerEnemyField);
        secondPlayer = new Computer(secondPlayerField, secondPlayerEnemyField);
        status = Status.PLAY;
    }

    public void init() {
        firstPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        secondPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        firstPlayer.setShootBehavior(new ImprovedRandomShootStrategy(firstPlayer));
        secondPlayer.setShootBehavior(new RandomShootStrategy());
        firstPlayer.arrangeShips();
        secondPlayer.arrangeShips();
        numberOfDecks = firstPlayerField.countNumberOfDecks();
    }

    public void play() {
        Point shoot;
        Field.Answer answer;
        Player currentPlayer = firstPlayer;
        Field currentShelledField = secondPlayerField;
        Field currentPlayerEnemyField = firstPlayerEnemyField;

        while (status == Status.PLAY) {
            shoot = currentPlayer.shoot();
            answer = currentShelledField.update(shoot);
            currentPlayerEnemyField.update(shoot, answer);
            show();
            if (answer == Field.Answer.GET || answer == Field.Answer.SUNK) {
                currentPlayer.addHitCount();
                currentPlayer.setLastHit(shoot);
                changeGameStatus();
            }
            if (answer == Field.Answer.MISS) {
                currentPlayer.setLastHit(null);
                currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
                currentShelledField = currentShelledField == secondPlayerField ? firstPlayerField : secondPlayerField;
                currentPlayerEnemyField = currentPlayerEnemyField == firstPlayerEnemyField
                        ? secondPlayerEnemyField : firstPlayerEnemyField;
            }
        }
        System.out.println(status);
    }

    private void changeGameStatus() {
        if (firstPlayer.getHitCount() == numberOfDecks) {
            status = Status.FIRST_WIN;
        }
        if (secondPlayer.getHitCount() == numberOfDecks) {
            status = Status.SECOND_WIN;
        }

    }

    private void show() {
        viewable.show(firstPlayerField, firstPlayerEnemyField);
    }

    public void setViewable(Viewable viewable) {
        this.viewable = viewable;
    }
}
