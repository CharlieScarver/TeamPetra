package game;

import java.io.File;
import java.util.Locale;

// game is launched here, must be as small as possible - no logic here.
public class Launcher {
    public static  void main(String[] args) {
        Game game = new Game("SoftUni Fighter", 1024, 768);
        game.start();
    }
}
