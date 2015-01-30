package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private final int width = 300;
    private final int height = 200;

    public static BufferedImage player1, player2;

    public static void init() {
        player1 = new SpriteSheet(ImageLoader.loadImage("/Images/player.png"))
        .crop(0, 0, 300, 200);
    }
}
