package field;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayersField extends AbstractField {
    private Set<Point> unavailableCells;
    private int numberOfDecks;

    public PlayersField() {
        super();
        unavailableCells = new HashSet<>();
    }

/*
    public int countNumberOfDecks() {
        int count = 0;
        for (int row = 0; row < fieldHeight; row++) {
            for (int column = 0; column < fieldWidth; column++) {
                if (readFromCell(new Point(column, row)) == Cell.Sign.DECK) {
                    count++;
                }
            }
        }
        return count;
    }
*/

    public boolean addShip(Ship ship) {
        List<Point> points = ship.getPoints();
        for (Point point : points) {
            if (unavailableCells.contains(point)) {
                return false;
            }
        }
        for (Point point : points) {
            writeInCell(point, Cell.Sign.DECK);
        }
        numberOfDecks += points.size();
        addUnavailableCells(points);
        return true;
    }

    public Answer update(Point point) {
        Cell.Sign sign = readFromCell(point);
        Answer answer;

        switch (sign) {
            case EMPTY:
                answer = Answer.MISS;
                writeInCell(point, Cell.Sign.MARKED);
                break;
            case MARKED:
            case DESTROYED:
                answer = Answer.REPEAT;
                break;
            case DECK:
                if (isSunk(point)) {
                    answer = Answer.SUNK;
                } else {
                    answer = Answer.GET;
                }
                writeInCell(point, Cell.Sign.DESTROYED);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sign);
        }
        return answer;
    }

    private void addUnavailableCells(List<Point> points) {
        Point point;
        for (Point p : points) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int column = p.getColumn() + i;
                    int row = p.getRow() + j;
                    point = new Point(column, row);
                    unavailableCells.add(point);
                    if (readFromCell(point) == Cell.Sign.EMPTY) {
                    }
                }
            }
        }
    }

    private boolean isSunk(Point point) {
        int column = point.getColumn();
        int row = point.getRow();
        Point neighboringCell;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 ^ j == 0) {
                    neighboringCell = new Point(column + i, row + j);
                    if (readFromCell(neighboringCell) == Cell.Sign.DECK) {
                        return false;
                    }
                    if (readFromCell(neighboringCell) == Cell.Sign.DESTROYED) {
                        writeInCell(point, Cell.Sign.MARKED);
                        boolean isSunk = isSunk(neighboringCell);
                        writeInCell(point, Cell.Sign.DESTROYED);
                        if (!isSunk) return false;
                    }
                }
            }
        }
        return true;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }
}
