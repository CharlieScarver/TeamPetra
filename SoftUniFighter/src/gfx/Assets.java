package gfx;

import java.awt.image.BufferedImage;
import java.io.File;

public class Assets {
    private final int width = 300;
    private final int height = 200;

    public static BufferedImage background,player1, player2;

    public static void init() {
        ///home/ksevery/TeamworkJava/TeamPetra/SoftUniFighter/src/gfx/ImageLoader.java
        background = ImageLoader.loadImage("images" + File.separator + "Arena1024.jpg");
        System.out.println(new File(".").getAbsolutePath());
    }
}
