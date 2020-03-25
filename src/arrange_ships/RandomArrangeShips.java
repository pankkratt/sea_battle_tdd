package arrange_ships;

import model.Game;

public class RandomArrangeShips implements ArrangeShipsBehavior {

    @Override
    public void arrangeShips() {
        for (int shipSize = Game.MAX_SHIP_SIZE; shipSize > 0; shipSize--) {
            for (int number = Game.MAX_SHIP_SIZE - shipSize + 1; number > 0; number--) {
                putShip(shipSize);
            }
        }
    }

    private void putShip(int shipSize) {
        System.out.printf("нарисован %d-палубный корабль%n", shipSize);
    }
}
