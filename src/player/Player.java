package player;

import field.Field;
import field.Point;

public abstract class Player {
    protected Field field;
    protected Field enemyField;
    protected ArrangeShipsBehavior arrangeShipsBehavior;
    protected ShootBehavior shootBehavior;

    public Player(Field field, Field enemyField) {
        this.field = field;
        this.enemyField = enemyField;
    }

    public void arrangeShips() {
        arrangeShipsBehavior.arrangeShips(field);
    }

    Point shoot() {
        shootBehavior.shoot();
        return null;
    }

    public void setArrangeShipsBehavior(ArrangeShipsBehavior arrangeShipsBehavior) {
        this.arrangeShipsBehavior = arrangeShipsBehavior;
    }

    public void setShootBehavior(ShootBehavior shootBehavior) {
        this.shootBehavior = shootBehavior;
    }


}

