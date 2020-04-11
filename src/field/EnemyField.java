package field;

import java.util.HashSet;
import java.util.Set;

public class EnemyField extends AbstractField {
    private Set<Point> ignoredCells;
    private Point lastGetting;

    public EnemyField() {
        super();
        ignoredCells = new HashSet<>();
    }

    public void update(Point point, Answer answer) {
        switch (answer) {
            case SUNK:
                writeInCell(point, Cell.Sign.DESTROYED);
                onSunk(point);
                lastGetting = null;
                break;
            case GET:
                onGet(point);
                writeInCell(point, Cell.Sign.DESTROYED);
                lastGetting = point;
                break;
            case MISS:
                ignoredCells.add(point);
                writeInCell(point, Cell.Sign.MARKED);
                break;
            case REPEAT:
                break;
        }
    }

    private void onSunk(Point point) {
        int column = point.getColumn();
        int row = point.getRow();
        Point neighboringCell;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                neighboringCell = new Point(column + i, row + j);
                ignoredCells.add(neighboringCell);
                if (readFromCell(neighboringCell) == Cell.Sign.DESTROYED) {
                    if (i == 0 ^ j == 0) {
                        writeInCell(point, Cell.Sign.EMPTY);
                        onSunk(neighboringCell);
                        writeInCell(point, Cell.Sign.DESTROYED);
                    }
                }
            }
        }
    }

    private void onGet(Point point) {
        int column = point.getColumn();
        int row = point.getRow();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i == 0) == (j == 0)) {
                    ignoredCells.add(new Point(column + i, row + j));
                }
            }
        }
    }

    public Set<Point> getIgnoredCells() {
        return new HashSet<>(ignoredCells);
    }

    public Point getLastGetting() {
        return lastGetting;
    }
}
