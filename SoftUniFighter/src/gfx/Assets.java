package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    private final int width = 300;
    private final int height = 200;

    public static BufferedImage background,player1, player2;

    public static void init() {
        background = ImageLoader.loadImage("SoftUniFighter/images/Arena1MortalKombat.png");
    }
}
