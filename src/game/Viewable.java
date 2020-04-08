package game;

import field.AbstractField;

interface Viewable {
    void show(AbstractField field, AbstractField enemyField);
}
