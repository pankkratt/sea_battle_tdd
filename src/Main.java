import game.Game;
import game.ShowInConsoleStrategy;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.setShowBehavior(new ShowInConsoleStrategy());
        game.init();
        game.play();

    }
}
