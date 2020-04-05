package player;

import field.Field;
import field.Point;
import field.Ship;
import game.Game;

public class RandomArrangeShipsStrategy implements ArrangeShipsBehavior {

    @Override
    public void arrangeShips(Field field) {
        int numDecks = Game.MAX_NUM_DECKS;
        Ship ship;
        for (int i = numDecks; i > 0; i--) {
            for (int j = numDecks + 1 - i; j > 0; j--) {
                do {
                    ship = new Ship(i);
                } while (!field.addShip(ship));
            }
        }
    }

}
