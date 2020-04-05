package field;

public class Point {
    private int column;
    private int row;

    @Override
    public String toString() {
        return "Point{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    public Point(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
