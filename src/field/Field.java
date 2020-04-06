package field;

import java.util.List;

public class Field {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private Cell[][] cells;
    public enum Answer {
        GET,
        MISS,
        REPEAT,
        SUNK,
    }

    public Field() {
        cells = new Cell[WIDTH][HEIGHT];
        init();
    }

    private void init() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                cells[column][row] = new Cell();
            }
        }
    }

    public int countNumberOfDecks() {
        int count = 0;
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                if (readFromCell(new Point(column, row)) == Cell.Sign.DECK) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean addShip(Ship ship) {
        List<Point> points = ship.getPoints();
        if (isEnoughPlace(points)) {
            for (Point point : points) {
                writeInCell(point, Cell.Sign.DECK);
            }
            markUnavailableCells(points);
            return true;
        } else {
            return false;
        }
    }

    public Cell.Sign readFromCell(Point point) {
        int column = point.getColumn();
        int row = point.getRow();
        if (column >= 0 && column < WIDTH && row >= 0 && row < HEIGHT) {
            Cell cell = cells[column][row];
            return cell.getSign();
        }
        return Cell.Sign.UNAVAILABLE;
    }

    public void writeInCell(Point point, Cell.Sign sign) {
        int column = point.getColumn();
        int row = point.getRow();
        if (column >= 0 && column < WIDTH && row >= 0 && row < HEIGHT) {
            Cell cell = cells[column][row];
            cell.setSign(sign);
        }
    }

    public Answer update(Point point) {
        Cell.Sign sign = readFromCell(point);
        Answer answer;

        switch (sign) {
            case EMPTY:
            case UNAVAILABLE:
                answer = Answer.MISS;
                writeInCell(point, Cell.Sign.MARKED);
                break;
            case MARKED:
            case DESTROYED:
                answer = Answer.REPEAT;
                break;
            case DECK:
                if (isSunk()) {
                    answer = Answer.SUNK;
                } else {
                    // TODO: 3/30/2020
                    answer = Answer.GET;
                }
                writeInCell(point, Cell.Sign.DESTROYED);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sign);
        }
        return answer;
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

    public boolean isSunk() {
        return false;
    }

    public void markNeighboringCells(Point point, Cell.Sign sign) {
        // TODO: 3/30/2020
    }

    private boolean isEnoughPlace(List<Point> points) {
        for (Point point : points) {
            if (readFromCell(point) != Cell.Sign.EMPTY) {
                return false;
            }
        }
        return true;
    }

    private void markUnavailableCells(List<Point> points) {
        Point point;
        for (Point p : points) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int column = p.getColumn() + i;
                    int row = p.getRow() + j;
                    point = new Point(column, row);
                    if (readFromCell(point) == Cell.Sign.EMPTY) {
                        writeInCell(point, Cell.Sign.UNAVAILABLE);
                    }
                }
            }
        }
    }
}
