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
        //this.display.addKeyListener(new InputHandler());
        Sound.music();

        Assets.init();
        this.currentState = StateManager.getCurrentState();

        // Initialize players here.

        // Nakov
        this.player1 = new Player(0, 240, CharacterEnum.Nakov);

        this.player1.setPlayerStationary(Assets.nakovStationary);
        this.player1.setPlayerWalk(Assets.nakovWalk);
        this.player1.setPlayerPunch(Assets.nakovPunch);
        this.player1.setPlayerKick(Assets.nakovKick);
        this.player1.setPlayerBlock(Assets.nakovBlock);

        this.player1.setReversePlayerStationary(Assets.nakovRevStationary);
        this.player1.setReversePlayerWalk(Assets.nakovRevWalk);
        this.player1.setReversePlayerPunch(Assets.nakovRevPunch);
        this.player1.setReversePlayerKick(Assets.nakovRevKick);
        this.player1.setReversePlayerBlock(Assets.nakovRevBlock);

        // Prof
        this.player2 = new Player(590, 240, CharacterEnum.Prof);

        this.player2.setPlayerStationary(Assets.profStationary);
        this.player2.setPlayerWalk(Assets.profWalk);
        this.player2.setPlayerPunch(Assets.profPunch);
        this.player2.setPlayerKick(Assets.profKick);
        this.player2.setPlayerBlock(Assets.profBlock);

        this.player2.setReversePlayerStationary(Assets.profRevStationary);
        this.player2.setReversePlayerWalk(Assets.profRevWalk);
        this.player2.setReversePlayerPunch(Assets.profRevPunch);
        this.player2.setReversePlayerKick(Assets.profRevKick);
        this.player2.setReversePlayerBlock(Assets.profRevBlock);

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

        this.player1.update();
        this.player2.update();

        if (this.player1.intersects(player2) && !this.player2.isBlocking()) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
            if (this.player1.getKicking() == ActionScene ) {
                this.player2.getHit(10);

            } else if (this.player1.getPunching() == ActionScene) {
                this.player2.getHit(5);
            }
        } else if (this.player1.intersects(player2) && this.player2.isBlocking()) {
            if (this.player1.getPunching() == ActionScene || this.player1.getKicking() == ActionScene) {
                this.player2.getHit(2);
            }
        }

        if (this.player2.intersects(player1) && !this.player1.isBlocking()) {
            //intersection logic - is in attacking stance, what happens, reduce health, etc.
            if (this.player2.getKicking() == ActionScene) {
                this.player1.getHit(10);
            } else if (this.player2.getPunching() == ActionScene) {
                this.player1.getHit(5);
            }
        } else if (this.player2.intersects(player1) && this.player1.isBlocking()) {
            if (this.player2.getPunching() == ActionScene || this.player2.getKicking() == ActionScene) {
                this.player1.getHit(2);
            }
        }

        this.player1.checkReverse(player2);
        this.player2.checkReverse(player1);

    }

    // All drawing goes here.
    private void render() {
        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);
            return;
        }

        this.g = this.bs.getDrawGraphics();

        this.g.drawImage(Assets.background, 0, 0, null);

        this.player1.render(g);
        this.player2.render(g);

        if (this.player1.getHealth() > 0 && this.player2.getHealth() > 0) {
            int player1HealthBar = (int)(this.player1.getHealth() * 1.5);
            int player2HealthBar = (int)(this.player2.getHealth() * 1.5);
            this.g.setColor(Color.red);
            this.g.fillRect(20, 5, player1HealthBar, 20);
            this.g.fillRect(this.width - player2HealthBar - 20, 5, player2HealthBar, 20);
        } else if (this.player1.getHealth() <= 0) {
            drawWinner(this.player2);
        } else if (this.player2.getHealth() <= 0) {
            drawWinner(this.player1);
        }

        this.bs.show();
        this.g.dispose();
    }

    private void drawWinner (Player winner) {
        this.g.setColor(Color.white);
        this.g.fillRect((width / 2) - 105, 235, 220, 20);
        this.g.setColor(Color.black);

        if (winner.getIdentity().equals(CharacterEnum.Nakov)) {
            this.g.drawString("Nakov won!", width / 2 - 30, 250);
            this.g.drawImage(ImageLoader.loadImage("images" + File.separator + "NakovWin.png"), width / 2 - 75, 30, null);
        } else {
            this.g.drawString("The Professor won!", width / 2 - 50, 250);
            this.g.drawImage(ImageLoader.loadImage("images" + File.separator + "ProfWin.png"), width / 2 - 75, 30, null);
        }
        this.isRunning = false;
        // End Game
    }

}
