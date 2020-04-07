package player;

import field.Point;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class InputFromUserStrategy implements ShootBehavior {
    private InputStream source;
    private Scanner sc;

    public InputFromUserStrategy() {
        this(System.in);
    }

    public InputFromUserStrategy(InputStream source) {
        this.source = source;
        sc = new Scanner(source);
    }

    @Override
    public Point shoot() {
        String s = sc.nextLine();
        return new Point(s);
    }

    public void setSource(InputStream source) {
        this.source = source;
    }
}
