package game;

import field.Field;
import field.Point;
import player.*;

import java.util.Scanner;

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
        secondPlayer.setArrangeShipsBehavior(new RandomArrangeShipsStrategy());
        firstPlayer.setShootBehavior(new RandomShootStrategy());
        secondPlayer.setShootBehavior(new RandomShootStrategy());
        firstPlayer.arrangeShips();
        secondPlayer.arrangeShips();
    }

    public void play() {
        Point shoot;
        Field.Answer answer;
        Player currentPlayer = firstPlayer;
        Field currentShelledField = secondPlayerField;
        Field currentPlayerEnemyField = firstPlayerEnemyField;

        while (true) {
            shoot = currentPlayer.shoot();
            answer = currentShelledField.update(shoot);
            currentPlayerEnemyField.update(shoot, answer);
            show();
            if (answer == Field.Answer.MISS) {
                currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
                currentShelledField = currentShelledField == secondPlayerField ? firstPlayerField : secondPlayerField;
                currentPlayerEnemyField = currentPlayerEnemyField == firstPlayerEnemyField
                        ? secondPlayerEnemyField : firstPlayerEnemyField;
            }

            Scanner sc = new Scanner(System.in);
            sc.nextLine();
        }

    }

    private void show() {
        viewable.show(firstPlayerField, firstPlayerEnemyField);
    }

    public void setViewable(Viewable viewable) {
        this.viewable = viewable;
    }
}
