package game;

import game.gameObjects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            Game.getPlayer1().setVelX(1);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            Game.getPlayer1().setVelX(-1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_LEFT) {
            //Stop
            Game.getPlayer1().setVelX(0);
        }
    }
}
