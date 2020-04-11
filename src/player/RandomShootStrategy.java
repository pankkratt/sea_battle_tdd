package player;

import field.EnemyField;
import field.Point;

public class RandomShootStrategy implements ShootBehavior {
    protected EnemyField enemyField;

    public RandomShootStrategy(EnemyField enemyField) {
        this.enemyField = enemyField;
    }

    @Override
    public Point shoot() {
        return new Point();
    }
}
