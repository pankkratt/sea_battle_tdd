public class Cell {

    private int column;
    private int row;
    private Sign sign;

    public Cell(int column, int row) {
        this.column = column;
        this.row = row;
        sign = Sign.EMPTY;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Sign getSign() {
        return sign;
    }


    public enum Sign {DECK, EMPTY}
}
