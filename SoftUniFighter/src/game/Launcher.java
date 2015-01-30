package game;

// game is launched here, must be as small as possible - no logic here.
public class Launcher {
    public static  void main(String[] args) {
        Game game = new Game("SoftUni Fighter", 800, 600);
        game.start();
    }
}
