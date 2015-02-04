package gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Assets {

    public static BufferedImage bg1, bg2, bg3;
    public static BufferedImage background;

    public static BufferedImage nakovStationary, nakovWalk;
    public static BufferedImage nakovPunch, nakovKick;
    public static BufferedImage nakovBlock;

    public static BufferedImage nakovRevStationary, nakovRevWalk;
    public static BufferedImage nakovRevPunch, nakovRevKick;
    public static BufferedImage nakovRevBlock;

    public static BufferedImage profStationary, profWalk;
    public static BufferedImage profPunch, profKick;
    public static BufferedImage profBlock;

    public static BufferedImage profRevStationary, profRevWalk;
    public static BufferedImage profRevPunch, profRevKick;
    public static BufferedImage profRevBlock;

    public static BufferedImage nakovWin, profWin;

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

        // Nakov Default Pictures
        nakovStationary = ImageLoader.loadImage("images" + File.separator + "NakovHeadDefaultFighter1PNG.png");
        nakovWalk = ImageLoader.loadImage("images" + File.separator + "NakovWalk.png");
        nakovPunch = ImageLoader.loadImage("images" + File.separator + "NakovPunch.png");
        nakovKick = ImageLoader.loadImage("images" + File.separator + "NakovKick.png");
        nakovBlock = ImageLoader.loadImage("images" + File.separator + "NakovBlock.png");

        // Nakov Reversed Pictures
        nakovRevStationary = ImageLoader.loadImage("images" + File.separator + "MNakovHeadDefaultFighter1PNG.png");
        nakovRevWalk = ImageLoader.loadImage("images" + File.separator + "MNakovWalk.png");
        nakovRevPunch = ImageLoader.loadImage("images" + File.separator + "MNakovPunch.png");
        nakovRevKick = ImageLoader.loadImage("images" + File.separator + "MNakovKick.png");
        nakovRevBlock = ImageLoader.loadImage("images" + File.separator + "MNakovBlock.png");

        // Prof Default Pictures
        profStationary = ImageLoader.loadImage("images" + File.separator + "ProfNormal.png");
        profWalk = ImageLoader.loadImage("images" + File.separator + "ProfWalk.png");
        profPunch = ImageLoader.loadImage("images" + File.separator + "ProfPunch.png");
        profKick = ImageLoader.loadImage("images" + File.separator + "ProfKick.png");
        profBlock = ImageLoader.loadImage("images" + File.separator + "ProfBlock.png");

        // Prof Reversed Pictures
        profRevStationary = ImageLoader.loadImage("images" + File.separator + "MProfNormal.png");
        profRevWalk = ImageLoader.loadImage("images" + File.separator + "MProfWalk.png");
        profRevPunch = ImageLoader.loadImage("images" + File.separator + "MProfPunch.png");
        profRevKick = ImageLoader.loadImage("images" + File.separator + "MProfKick.png");
        profRevBlock = ImageLoader.loadImage("images" + File.separator + "MProfBlock.png");

        // Winning images
        nakovWin = ImageLoader.loadImage("images" + File.separator + "NakovWin.png");
        profWin = ImageLoader.loadImage("images" + File.separator + "ProfWin.png");
    }
}
