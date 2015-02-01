package game;

import display.Display;
import game.gameObjects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    private Player player1;
    private Player player2;

    public InputHandler(Display display, Player p1, Player p2) {
        display.getCanvas().addKeyListener(this);
        this.player1 = p1;
        this.player2 = p2;
}

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1 - Nakov
        if (keyCode == KeyEvent.VK_D) {
            //Go right
            this.player1.movingRight = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            //Go left
            this.player1.movingLeft = true;
        }

        if (keyCode == KeyEvent.VK_G) {
            if (this.player1.kicking == 0) {
                // Punch
                this.player1.punching = 1;
            }
        } else if (keyCode == KeyEvent.VK_H) {
            if (this.player1.punching == 0) {
                // Kick
                this.player1.kicking = 1;
            }
        }

        // Player 2 - Prof
        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            this.player2.movingRight = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            this.player2.movingLeft = true;
        }

        if (keyCode == KeyEvent.VK_K) {
            if (this.player2.kicking == 0) {
                // Punch
                this.player2.punching = 1;
            }
        } else if (keyCode == KeyEvent.VK_L) {
            if (this.player2.punching == 0) {
                // Kick
                this.player2.kicking = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1 - Nakov
        if (keyCode == KeyEvent.VK_D) {
            //Go right
            this.player1.movingRight = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            //Go left
            this.player1.movingLeft = false;
        }

        // Player 2 - Prof
        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            this.player2.movingRight = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            this.player2.movingLeft = false;
        }
    }
}
