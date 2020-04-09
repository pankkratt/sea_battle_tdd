package player;

import field.Point;

public class ImprovedRandomShootStrategy implements ShootBehavior {
    private Player player;

    public ImprovedRandomShootStrategy(Player player) {
        this.player = player;
    }

    @Override
    public Point shoot() {
        Point point;
        do {
            point = new Point();
        } while (player.getIgnoredCells().contains(point));
        return point;
    }
}
