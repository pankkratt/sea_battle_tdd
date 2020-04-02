package player;

import field.Cell;
import field.Field;
import field.Point;

public class RandomArrangeShipsStrategy implements ArrangeShipsBehavior {

    @Override
    public void arrangeShips(Field field) {
        field.writeInCell(new Point(3,2), Cell.Sign.DECK);
        field.writeInCell(new Point(3,3), Cell.Sign.DECK);
        field.writeInCell(new Point(3,4), Cell.Sign.DECK);
        field.writeInCell(new Point(3,5), Cell.Sign.DECK);
    }
}
