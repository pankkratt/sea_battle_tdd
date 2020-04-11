package player;

import field.AbstractField;
import field.Cell;
import field.EnemyField;
import field.Point;

import java.util.Set;


public class FinishHimShootStrategy extends ImprovedRandomShootStrategy {
    public FinishHimShootStrategy(EnemyField enemyField) {
        super(enemyField);
    }

    @Override
    public Point shoot() {
        Point lastLuckyShot = enemyField.getLastGetting();
        if (lastLuckyShot != null) {
            return shoot(lastLuckyShot);
        } else {
            return super.shoot();
        }
    }

    private Point shoot(Point shot) {
        int column = shot.getColumn();
        int row = shot.getRow();
        Point neighboringCell;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 ^ j == 0) {
                    neighboringCell = new Point(column + i, row + j);
                    if (enemyField.readFromCell(neighboringCell) == null) {
                        continue;
                    }
                    if (!enemyField.getIgnoredCells().contains(neighboringCell)) {
                        return neighboringCell;
                    } else if (enemyField.readFromCell(neighboringCell) == Cell.Sign.DESTROYED) {
                        enemyField.writeInCell(neighboringCell, Cell.Sign.MARKED);
                        shot = shoot(neighboringCell);
                        enemyField.writeInCell(neighboringCell, Cell.Sign.DESTROYED);
                    }
                }
            }
        }
        return shot;
    }
}
