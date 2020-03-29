package field;

class Cell {
    private Sign sign;
    enum Sign {
        DECK,
        DESTROYED,
        EMPTY,
        MARKED,
    }

    Cell() {
        sign = Sign.EMPTY;
    }

    void setSign(Sign sign) {
        this.sign = sign;
    }

    Sign getSign() {
        return sign;
    }
}
