package game;

import display.*;
import game.gameObjects.Player;
import gfx.Assets;
import state.State;
import state.StateManager;

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
    private State currentState;

    private Player player1;
    private Player player2;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);
        Assets.init();
        currentState = StateManager.getCurrentState();
        // Initialize players here.
        //this.player1 = new Player()
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

        int fps = 30;
        double timePerTick = 1000000000.0 / fps;
        double deltaTime = 0;
        long lastTimeTicked = System.nanoTime();
        long now;

        while (isRunning) {
            now = System.nanoTime();
            deltaTime += (now - lastTimeTicked) / timePerTick;
            lastTimeTicked = now;

            if (deltaTime >= 1) {
                this.update();
                this.render();
                deltaTime--;
            }
        }

        stop();
    }
// Can also be tick(), logic goes here.
    private void update() {
        this.player1.update();
        this.player2.update();
        if (this.player1.intersects(player2.getBoundingBox())) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
        }
    }
// All drawing goes here.
    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            display.getCanvas().createBufferStrategy(2);
            return;
        }

        g = this.bs.getDrawGraphics();

        //Draw here.
        this.player1.render(g);
        this.player2.render(g);

        this.bs.show();
        this.g.dispose();
    }
}
