public class Field {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    private Cell[][] cells;

    public Field() {
        cells = new Cell[WIDTH][HEIGHT];
    }

    public void init() {
        for (int row = 0; row < HEIGHT; row++) {
            for (int column = 0; column < WIDTH; column++) {
                cells[column][row] = new Cell(column, row);
            }
        }
    }

    public Cell.Sign readInCell(int column, int row) {
        Cell cell = cells[column][row];
        return cell.getSign();
    }

    public void writeInCell(int column, int row, Cell.Sign sign) {
        Cell cell = cells[column][row];
        cell.setSign(sign);
    }
}
