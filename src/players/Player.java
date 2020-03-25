package players;

import arrange_ships.ArrangeShipsBehavior;
import model.Field;

public abstract class Player {
    Field field;
    ArrangeShipsBehavior arrangeShipsBehavior;

    public Player() {
        field = new Field();
    }

    public void arrangeShips() {
        arrangeShipsBehavior.arrangeShips();
    }
}
