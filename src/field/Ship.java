package field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {
    Random random = new Random();
    private Point from;
    private Orientation orientation;
    private int length;
    private List<Point> points;
    public enum Orientation {
        HORIZONTAL,
        VERTICAL,
    }

    public Ship(int length) {
        points = new ArrayList<>();
        this.length = length;
        setFrom();
        setOrientation();
        init();
    }

    public List<Point> getPoints() {
        List<Point> clonedPoints = new ArrayList<>();
        clonedPoints.addAll(points);
        return clonedPoints;
    }

    private void init() {
        int fromColumn = from.getColumn();
        int fromRow = from.getRow();
        for (int i = 0; i < length; i++) {
            if (orientation == Orientation.HORIZONTAL) {
                points.add(new Point(fromColumn + i, fromRow));
            }
            if (orientation == Orientation.VERTICAL) {
                points.add(new Point(fromColumn, fromRow + i));
            }
        }
    }

    private void setOrientation() {

        if (random.nextBoolean()) {
            orientation = Orientation.VERTICAL;
        } else {
            orientation = Orientation.HORIZONTAL;
        }
    }

    private void setFrom() {
        int width = orientation == Orientation.HORIZONTAL ? Field.WIDTH : Field.WIDTH - length;
        int height = orientation == Orientation.VERTICAL ? Field.HEIGHT : Field.HEIGHT - length;
        int column = random.nextInt(width);
        int row = random.nextInt(height);
        from = new Point(column, row);
    }
}
