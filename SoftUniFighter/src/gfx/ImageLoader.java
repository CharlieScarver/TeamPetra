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
            try {
                return ImageIO.read(new File("SoftUniFighter" + File.separator + path));
            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
            }
        }

        return null;
    }
}
