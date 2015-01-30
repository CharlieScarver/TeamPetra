package Game;

import Display.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

// All of the game's logic goes here.
public class Game implements Runnable {
    private String title;
    private int width;
    private int height;

    private Thread thread;
    private  boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private Graphics g;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);

    }

    public synchronized void start() {
        if (this.isRunning) {
            return;
        }

        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() {
        if (!this.isRunning) {
            return;
        }

        this.isRunning = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.init();

        while (isRunning) {
            this.tick();
            this.render();
        }

        stop();
    }

    private void tick() {

    }
// All drawing goes here.
    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }

        g = this.bs.getDrawGraphics();

        this.bs.show();
        this.g.dispose();
    }
}
