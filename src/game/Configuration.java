package game;

public class Configuration {
    private static int fieldWidth = 10;
    private static int fieldHeight = 10;

    public static int getFieldWidth() {
        return fieldWidth;
    }

    public static void setFieldWidth(int fieldWidth) {
        Configuration.fieldWidth = fieldWidth;
    }

    public static int getFieldHeight() {
        return fieldHeight;
    }

    public static void setFieldHeight(int fieldHeight) {
        Configuration.fieldHeight = fieldHeight;
    }
}
