package field;

import game.Configuration;

public abstract class AbstractField {
    protected int fieldWidth;
    protected int fieldHeight;
    protected Cell[][] cells;
    public enum Answer {
        GET,
        MISS,
        REPEAT,
        SUNK,
    }

    public AbstractField() {
        fieldWidth = Configuration.getFieldWidth();
        fieldHeight = Configuration.getFieldHeight();
        cells = new Cell[fieldWidth][fieldHeight];
        init();
    }

    protected void init() {
        for (int row = 0; row < fieldHeight; row++) {
            for (int column = 0; column < fieldWidth; column++) {
                cells[column][row] = new Cell();
            }
        }
    }

    public Cell.Sign readFromCell(Point point) {
        int column = point.getColumn();
        int row = point.getRow();
        if (column >= 0 && column < fieldWidth && row >= 0 && row < fieldHeight) {
            Cell cell = cells[column][row];
            return cell.getSign();
        }
        return null;
    }

    public void writeInCell(Point point, Cell.Sign sign) {
        int column = point.getColumn();
        int row = point.getRow();
        if (column >= 0 && column < fieldWidth && row >= 0 && row < fieldHeight) {
            Cell cell = cells[column][row];
            cell.setSign(sign);
        }
    }
}
