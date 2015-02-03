package game;

import display.*;
import game.gameObjects.CharacterEnum;
import game.gameObjects.Player;
import game.gameObjects.Sound;
import gfx.Assets;
import gfx.ImageLoader;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

// All of the game's logic goes here.

public class Game implements Runnable {
    private static final int ActionScene = 8;

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
        Sound.music();

        Assets.init();
        currentState = StateManager.getCurrentState();

        // Initialize players here.
        player1 = new Player(0, 240, CharacterEnum.Nakov,
                "images" + File.separator + "NakovHeadDefaultFighter1PNG.png",
                "images" + File.separator + "NakovWalk.png",
                "images" + File.separator + "NakovPunch.png",
                "images" + File.separator + "NakovKick.png");

        player1.setReversePlayerStationary(ImageLoader.loadImage("images" + File.separator + "MNakovHeadDefaultFighter1PNG.png"));
        player1.setReversePlayerWalk(ImageLoader.loadImage("images" + File.separator + "MNakovWalk.png"));
        player1.setReversePlayerPunch(ImageLoader.loadImage("images" + File.separator + "MNakovPunch.png"));
        player1.setReversePlayerKick(ImageLoader.loadImage("images" + File.separator + "MNakovKick.png"));

        player2 = new Player(590, 240, CharacterEnum.Prof,
                "images" + File.separator + "ProfNormal.png",
                "images" + File.separator + "ProfWalk.png",
                "images" + File.separator + "ProfPunch.png",
                "images" + File.separator + "ProfKick.png");

        player2.setReversePlayerStationary(ImageLoader.loadImage("images" + File.separator + "MProfNormal.png"));
        player2.setReversePlayerWalk(ImageLoader.loadImage("images" + File.separator + "MProfWalk.png"));
        player2.setReversePlayerPunch(ImageLoader.loadImage("images" + File.separator + "MProfPunch.png"));
        player2.setReversePlayerKick(ImageLoader.loadImage("images" + File.separator + "MProfKick.png"));

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

        int fps = 120;
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
            if (this.player1.getKicking() == ActionScene) {
                this.player2.setHealth(this.player2.getHealth() - 10);
                this.player2.pushBack();

            } else if (this.player1.getPunching() == ActionScene) {
                this.player2.setHealth(this.player2.getHealth() - 5);
                this.player2.pushBack();
            }
        }

        if (this.player2.intersects(player1)) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
            if (this.player2.getKicking() == ActionScene) {
                this.player1.setHealth(this.player1.getHealth() - 10);
                this.player1.pushBack();
            } else if (this.player2.getPunching() == ActionScene) {
                this.player1.setHealth(this.player1.getHealth() - 5);
                this.player1.pushBack();
            }
        }

        player1.checkReverse(player2);
        player2.checkReverse(player1);

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

        player1.render(g);
        player2.render(g);

        if (this.player1.getHealth() > 0 && this.player2.getHealth() > 0) {
            g.setColor(Color.red);
            g.fillRect(20, 5, this.player1.getHealth(), 20);
            g.fillRect(this.width - 120, 5, player2.getHealth(), 20);
        } else if (this.player1.getHealth() <= 0) {
            drawWinner(this.player2);
        } else if (this.player2.getHealth() <= 0) {
            drawWinner(this.player1);
        }

        this.bs.show();
        this.g.dispose();
    }

    private void drawWinner (Player winner) {
        g.setColor(Color.white);
        g.fillRect((width / 2) - 105, 235, 220, 20);
        g.setColor(Color.black);

        if (winner.getIdentity().equals(CharacterEnum.Nakov)) {
            g.drawString("Nakov won!", width / 2 - 30, 250);
            g.drawImage(ImageLoader.loadImage("images" + File.separator + "NakovWin.png"), width / 2 - 75, 30, null);
        } else {
            g.drawString("The Professor won!", width / 2 - 50, 250);
            g.drawImage(ImageLoader.loadImage("images" + File.separator + "ProfWin.png"), width / 2 - 75, 30, null);
        }
        this.isRunning = false;
        // End Game
    }

}
