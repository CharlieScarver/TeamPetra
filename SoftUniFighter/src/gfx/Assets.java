package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Assets {
    private final int width = 300;
    private final int height = 200;

    public static BufferedImage bg1, bg2, bg3;
    public static BufferedImage background;
    public static BufferedImage player1walk1, player1walk2;
    public static BufferedImage player1punch, player1kick;
    public static BufferedImage nakovBlock, nakovRevBlock;
    public static BufferedImage profBlock, profRevBlock;

    // public static BufferedImage player2walk1, player2walk2;

    public static void init() {

        bg1 = ImageLoader.loadImage("images" + File.separator + "Arena1PNG.png");
        bg2 = ImageLoader.loadImage("images" + File.separator + "Arena2.png");
        bg3 = ImageLoader.loadImage("images" + File.separator + "Arena3.png");

        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                background = bg1;
                break;
            case 1:
                background = bg2;
                break;
            case 2:
                background = bg3;
                break;
        }

        //System.out.println(new File(".").getAbsolutePath());
        player1walk1 = ImageLoader.loadImage("images" + File.separator + "NakovHeadDefaultFighter1PNG.png");
        player1walk2 = ImageLoader.loadImage("images" + File.separator + "NakovWalk.png");
        player1punch = ImageLoader.loadImage("images" + File.separator + "NakovPunch.png");
        player1kick = ImageLoader.loadImage("images" + File.separator + "NakovKick.png");
        nakovBlock = ImageLoader.loadImage("images" + File.separator + "NakovBlock.png");
        nakovRevBlock = ImageLoader.loadImage("images" + File.separator + "MNakovBlock.png");
        profBlock = ImageLoader.loadImage("images" + File.separator + "ProfBlock.png");
        profRevBlock = ImageLoader.loadImage("images" + File.separator + "MProfBlock.png");
    }
}
