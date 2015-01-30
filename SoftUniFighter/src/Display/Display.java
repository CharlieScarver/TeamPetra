package Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

// Drawing the display here.
public class Display extends Canvas{
    private int width;
    private int height;
    private String title;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        createFrame();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    private void createFrame() {
        this.frame = new JFrame();
        this.frame.setTitle(this.title);
        this.frame.setSize(this.width, this.height);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setFocusable(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.canvas = new Canvas();
        this.canvas.setSize(new Dimension(this.width, this.height));
        this.frame.add(canvas);
        this.frame.pack();
    }
}
