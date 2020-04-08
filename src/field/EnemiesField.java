package field;

import java.util.HashSet;
import java.util.Set;

public class EnemiesField extends AbstractField {
    private Set<Point> ignoredCells;

    public EnemiesField() {
        super();
        ignoredCells = new HashSet<>();
    }

    public void update(Point point, Answer answer) {
        switch (answer) {
            case GET:
                writeInCell(point, Cell.Sign.DESTROYED);
                break;
            case MISS:
                writeInCell(point, Cell.Sign.MARKED);
                break;
            case REPEAT:
                break;
            case SUNK:
                writeInCell(point, Cell.Sign.DESTROYED);
                markNeighboringCells(point, Cell.Sign.MARKED);
                break;
        }
        // TODO: 3/29/2020
    }

    public void markNeighboringCells(Point point, Cell.Sign sign) {
        // TODO: 3/30/2020
    }

}
