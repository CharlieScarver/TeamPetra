package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Short path not found.");
        }

        try {
            return ImageIO.read(new File("SoftUniFighter" + File.separator + path));
        } catch (IOException e) {
            System.out.println("Long path not found.");
        }

        return null;
    }
}
