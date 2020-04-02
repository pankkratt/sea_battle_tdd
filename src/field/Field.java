package field;

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

    public Cell.Sign readFromCell(Point point) {
        Cell cell = cells[point.getColumn()][point.getRow()];
        return cell.getSign();
    }

    public void writeInCell(Point point, Cell.Sign sign) {
        Cell cell = cells[point.getColumn()][point.getRow()];
        cell.setSign(sign);
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

    private void init() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                cells[column][row] = new Cell();
            }
        }
    }

    public boolean isSunk() {
        return false;
    }

    private void markNeighboringCells(Point point, Cell.Sign sign) {
        // TODO: 3/30/2020
    }
}
