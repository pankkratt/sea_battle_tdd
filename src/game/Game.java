package game;

import field.AbstractField;
import field.EnemiesField;
import field.PlayersField;
import field.Point;
import player.*;

public class Game {
    public static final int MAX_NUM_DECKS = 4;
    private Player firstPlayer;
    private Player secondPlayer;
    private PlayersField firstPlayerField;
    private EnemiesField firstPlayerEnemyField;
    private PlayersField secondPlayerField;
    private EnemiesField secondPlayerEnemyField;
    private Status status;
    private Viewable viewable;
    private int numberOfDecks;
    private enum Status {
        PLAY,
        FIRST_WIN,
        SECOND_WIN,
    }

    public Game() {
        firstPlayerField = new PlayersField();
        firstPlayerEnemyField = new EnemiesField();
        secondPlayerField = new PlayersField();
        secondPlayerEnemyField = new EnemiesField();
        firstPlayer = new Player(firstPlayerField, firstPlayerEnemyField);
        secondPlayer = new Player(secondPlayerField, secondPlayerEnemyField);
        status = Status.PLAY;
    }

    public void init() {
        firstPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        secondPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        firstPlayer.setShootBehavior(new InputFromUserStrategy());
        secondPlayer.setShootBehavior(new ImprovedRandomShootStrategy(firstPlayer));
        firstPlayer.arrangeShips();
        secondPlayer.arrangeShips();
        numberOfDecks = firstPlayerField.getNumberOfDecks();
        show();
    }

    public void play() {
        Point shoot;
        AbstractField.Answer answer;
        Player currentPlayer = firstPlayer;
        PlayersField currentShelledField = secondPlayerField;
        EnemiesField currentPlayerEnemyField = firstPlayerEnemyField;

        while (status == Status.PLAY) {
            shoot = currentPlayer.shoot();
            answer = currentShelledField.update(shoot);
            currentPlayerEnemyField.update(shoot, answer);
            show();
            System.out.println(answer);
            if (answer == PlayersField.Answer.GET || answer == PlayersField.Answer.SUNK) {
                currentPlayer.addHitCount();
                currentPlayer.setLastHit(shoot);
                changeGameStatus();
            }
            if (answer == PlayersField.Answer.MISS) {
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
