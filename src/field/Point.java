package field;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Point {
    private int column;
    private int row;

    public Point() {
        Random random = new Random();
        column = random.nextInt(Field.WIDTH);
        row = random.nextInt(Field.HEIGHT);
    }

    public Point(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public Point(String s) {
        column = s.charAt(0) - 'a';
        row = s.charAt(1) - '0' - 1;
        if (s.length() > 2) {
            row = row * 10 + s.charAt(2) - '0' + 9;
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "Point{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return column == point.column &&
                row == point.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
