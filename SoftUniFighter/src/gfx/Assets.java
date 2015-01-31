package gfx;

import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {
    private final int width = 300;
    private final int height = 200;

    public static BufferedImage background;
    public static BufferedImage player1walk1, player1walk2;
    public static BufferedImage player1punch, player1kick;

    // public static BufferedImage player2walk1, player2walk2;

    public static void init() {

        background = ImageLoader.loadImage("images" + File.separator + "Arena1024.jpg");
        //System.out.println(new File(".").getAbsolutePath());
        player1walk1 = ImageLoader.loadImage("images" + File.separator + "NakovHeadDefaultFighter1PNG.png");
        player1walk2 = ImageLoader.loadImage("images" + File.separator + "NakovWalk.png");
        player1punch = ImageLoader.loadImage("images" + File.separator + "NakovPunch.png");
        player1kick = ImageLoader.loadImage("images" + File.separator + "NakovKick.png");

    }
}
