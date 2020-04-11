package player;

import field.EnemyField;
import field.Point;

public class ImprovedRandomShootStrategy extends RandomShootStrategy {

    public ImprovedRandomShootStrategy(EnemyField enemyField) {
        super(enemyField);
    }

    @Override
    public Point shoot() {
        Point point;
        do {
            point = super.shoot();
        } while (enemyField.getIgnoredCells().contains(point));
        return point;
    }
}
