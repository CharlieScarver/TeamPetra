package game;

import display.*;
import game.gameObjects.CharacterEnum;
import game.gameObjects.Player;
import gfx.Assets;
import gfx.ImageLoader;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

// All of the game's logic goes here.
public class Game implements Runnable {
    private String title;
    private int width;
    private int height;

    private Thread thread;
    private  boolean isRunning;
    private Display display;

    private InputHandler inputHandler;
    private BufferStrategy bs;
    private Graphics g;
    private State currentState;

    public static Player player1;
    public static Player player2;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.isRunning = false;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);
        //this.display.addKeyListener(new InputHandler());

        Assets.init();
        currentState = StateManager.getCurrentState();

        // Initialize players here.
        player1 = new Player(0, 240, CharacterEnum.Nakov,
                "images" + File.separator + "NakovHeadDefaultFighter1PNG.png",
                "images" + File.separator + "NakovWalk.png",
                "images" + File.separator + "NakovPunch.png",
                "images" + File.separator + "NakovKick.png");


        player2 = new Player(590, 240, CharacterEnum.Prof,
                "images" + File.separator + "ProfNormal.png",
                "images" + File.separator + "ProfWalk.png",
                "images" + File.separator + "ProfPunch.png",
                "images" + File.separator + "ProfKick.png");


        this.inputHandler = new InputHandler(this.display, player1, player2);
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

        this.stop();
    }
// Can also be tick(), logic goes here.
    private void update() {
        player1.update();
        player2.update();
        if (this.player1.intersects(player2)) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
            if (this.player1.getKicking() == 1) {
                this.player2.setHealth(this.player2.getHealth() - 10);
                this.player2.pushBack();
            } else if (this.player1.getPunching() == 1) {
                this.player2.setHealth(this.player2.getHealth() - 5);
                this.player2.pushBack();
            }
        }

        if (this.player2.intersects(player1)) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
            if (this.player2.getKicking() == 1) {
                this.player1.setHealth(this.player1.getHealth() - 10);
                this.player1.pushBack();
            } else if (this.player2.getPunching() == 1) {
                this.player1.setHealth(this.player1.getHealth() - 5);
                this.player1.pushBack();
            }
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

        g.drawImage(Assets.background, 0, 0, null);
        g.drawString(String.valueOf(player1.getHealth()), 20, 20);
        g.drawString(String.valueOf(player2.getHealth()), this.width - 50, 20);
        //Draw here.
        player1.render(g);
        player2.render(g);

        this.bs.show();
        this.g.dispose();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
