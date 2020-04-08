package field;

public class Cell {
    private Sign sign;
    public enum Sign {
        DECK,
        DESTROYED,
        EMPTY,
        MARKED,
    }

    public Cell() {
        sign = Sign.EMPTY;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Sign getSign() {
        return sign;
    }
}
