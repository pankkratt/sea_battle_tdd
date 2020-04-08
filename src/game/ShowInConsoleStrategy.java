package game;

import field.*;

public class ShowInConsoleStrategy implements Viewable {
    StringBuilder display = new StringBuilder();

    @Override
    public void show(AbstractField field, AbstractField enemyField) {
//        String letters = "А　Б　В　Г　Д　Е　Ж　З　И　К";
        String letters = "A　B　C　D　E　F　G　H　I　J";

        display.append("\n\n　　");
        display.append(letters);
        display.append("　　　");
        display.append(letters);
        display.append("\n");
        for (int row = 0; row < Configuration.getFieldHeight(); row++) {
            for (AbstractField f : new AbstractField[]{field, enemyField}) {
                display.append((char) ('\uF146' + row));
                display.append(" ");
                getRow(row, f);
                display.append(" ");
            }
            display.append("\n");
        }
        System.out.println(display);
    }

    private void getRow(int row, AbstractField field) {
        for (int column = 0; column < Configuration.getFieldWidth(); column++) {
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
                return '\uE739';
            case DECK:
                return '\uE73B';
        }
        return 0;
    }
}
