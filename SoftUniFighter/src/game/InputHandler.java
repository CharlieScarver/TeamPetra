package game;

import display.Display;
import game.gameObjects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    public InputHandler(Display display) {
        display.getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            Game.player1.movingRight = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            Game.player1.movingLeft = true;
        }

        if (keyCode == KeyEvent.VK_K) {
            // Punch
            Game.player1.punching = 1;
        } else if (keyCode == KeyEvent.VK_L) {
            // Kick
            Game.player1.kicking = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            Game.player1.movingRight = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            Game.player1.movingLeft = false;
        }
    }
}
