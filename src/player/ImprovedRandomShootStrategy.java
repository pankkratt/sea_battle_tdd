package player;

import field.Point;

import java.util.HashSet;
import java.util.Set;

public class ImprovedRandomShootStrategy implements ShootBehavior {
    private Set<Point> ignoredPoints;
    private Player player;

    public ImprovedRandomShootStrategy(Player player) {
        this.player = player;
        ignoredPoints = new HashSet<>();
    }

    @Override
    public Point shoot() {
        Point point;
        point = player.getLastHit();
        if (point != null) {
            for (int i : new int[]{-1, 1}) {
                for (int j : new int[]{-1, 1}) {
                    int column = point.getColumn() + i;
                    int row = point.getRow() + j;
                    ignoredPoints.add(new Point(column, row));
                }
            }
        }
        do {
            point = new Point();
        } while (ignoredPoints.contains(point));
        ignoredPoints.add(point);
        return point;
    }
}
