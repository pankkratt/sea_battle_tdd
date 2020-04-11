package player;

import field.EnemyField;
import field.PlayersField;
import field.Point;

public class Player {
    protected PlayersField field;
    protected EnemyField enemyField;
    protected ArrangeShipsBehavior arrangeShipsBehavior;
    protected ShootBehavior shootBehavior;
    protected int hitCount;

    public Player(PlayersField field, EnemyField enemyField) {
        this.field = field;
        this.enemyField = enemyField;
    }

    public void arrangeShips() {
        arrangeShipsBehavior.arrangeShips(field);
    }

    public Point shoot() {
        return shootBehavior.shoot();
    }

    public void setArrangeShipsBehavior(ArrangeShipsBehavior arrangeShipsBehavior) {
        this.arrangeShipsBehavior = arrangeShipsBehavior;
    }

    public void setShootBehavior(ShootBehavior shootBehavior) {
        this.shootBehavior = shootBehavior;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void addHitCount() {
        hitCount++;
    }
}

