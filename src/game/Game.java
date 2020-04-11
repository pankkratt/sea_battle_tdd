package game;

import field.AbstractField;
import field.EnemyField;
import field.PlayersField;
import field.Point;
import player.*;

public class Game {
    public static final int MAX_NUM_DECKS = 4;
    private Player firstPlayer;
    private Player secondPlayer;
    private PlayersField firstPlayerField;
    private EnemyField firstPlayerEnemyField;
    private PlayersField secondPlayerField;
    private EnemyField secondPlayerEnemyField;
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
        firstPlayerEnemyField = new EnemyField();
        secondPlayerField = new PlayersField();
        secondPlayerEnemyField = new EnemyField();
        firstPlayer = new Player(firstPlayerField, firstPlayerEnemyField);
        secondPlayer = new Player(secondPlayerField, secondPlayerEnemyField);
        status = Status.PLAY;
    }

    public void init() {
        firstPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        secondPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        firstPlayer.setShootBehavior(new FinishHimShootStrategy(firstPlayerEnemyField));
        secondPlayer.setShootBehavior(new RandomShootStrategy(secondPlayerEnemyField));
        firstPlayer.arrangeShips();
        secondPlayer.arrangeShips();
        numberOfDecks = firstPlayerField.getNumberOfDecks();
        show();
    }

    public void play() {
        Point shot;
        AbstractField.Answer answer;
        Player currentPlayer = firstPlayer;
        PlayersField currentShelledField = secondPlayerField;
        EnemyField currentPlayerEnemyField = firstPlayerEnemyField;

        while (status == Status.PLAY) {
            shot = currentPlayer.shoot();
            answer = currentShelledField.update(shot);
            currentPlayerEnemyField.update(shot, answer);
            show();
            System.out.println(answer); // TODO: 4/10/2020  
            // begin temp code
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // TODO: 4/10/2020
            // end temp code
            if (answer == AbstractField.Answer.GET || answer == AbstractField.Answer.SUNK) {
                currentPlayer.addHitCount();
                changeGameStatus();
            }
            if (answer == PlayersField.Answer.MISS) {
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
