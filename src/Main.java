import game.Game;
import game.ShowInConsoleStrategy;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.getProperties().list(System.out);
        Game game = new Game();
        game.setViewable(new ShowInConsoleStrategy());
        game.init();
        game.play();

    }
}
