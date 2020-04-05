package game;

import field.Cell;
import field.Field;
import field.Point;

public class ShowInConsoleStrategy implements Viewable {
    StringBuilder display = new StringBuilder();

    @Override
    public void show(Field field, Field enemyField) {
//        String letters = "А　Б　В　Г　Д　Е　Ж　З　И　К";
        String letters = "A　B　C　D　E　F　G　H　I　J";

        display.append("\n\n　　");
        display.append(letters);
        display.append("　　　");
        display.append(letters);
        display.append("\n");
        for (int row = 0; row < Field.HEIGHT; row++) {
            for (Field f : new Field[]{field, enemyField}) {
                display.append((char) ('\uF146' + row));
                display.append(" ");
                getRow(row, f);
                display.append(" ");
            }
            display.append("\n");
        }
        System.out.println(display);
    }

    private void getRow(int row, Field field) {
        for (int column = 0; column < Field.WIDTH; column++) {
            Cell.Sign sign = field.readFromCell(new Point(column, row));
            display.append(getValueOfSign(sign));
            display.append(" ");
        }
    }

    private char getValueOfSign(Cell.Sign sign) {
        //　
        switch (sign) {
            case DESTROYED:
                return '\uE73D';
            case MARKED:
                return '\uE73A';
            case EMPTY:
            case UNAVAILABLE:
                return '\uE739';
            case DECK:
                return '\uE73B';
        }
        return 0;
    }
}
