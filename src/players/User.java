package players;

import arrange_ships.RandomArrangeShips;

public class User extends Player {
    public User() {
        arrangeShipsBehavior = new RandomArrangeShips();
    }
}
