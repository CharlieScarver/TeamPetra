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
            this.player1.setMovingRight(true);
            this.player1.setMovingLeft(false);
        }
        if (keyCode == KeyEvent.VK_A) {
            //Go left
            this.player1.setMovingLeft(true);
            this.player1.setMovingRight(false);
        }

        if (keyCode == KeyEvent.VK_G && this.player1.isCanHit()) {
            if (this.player1.getKicking() == 0) {
                // Punch
                this.player1.setPunching(1);
            }
        } else if (keyCode == KeyEvent.VK_H && this.player1.isCanHit()) {
            if (this.player1.getPunching() == 0) {
                // Kick
                this.player1.setKicking(1);
            }
        }

        // Player 2 - Prof
        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            this.player2.setMovingRight(true);
            this.player2.setMovingLeft(false);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            this.player2.setMovingLeft(true);
            this.player2.setMovingRight(false);
        }

/*        if (keyCode == KeyEvent.VK_K) {
            if (this.player2.getKicking() == 0) {
                // Punch
                this.player2.setPunching(1);
            }
        } else if (keyCode == KeyEvent.VK_L) {
            if (this.player2.getPunching() == 0) {
                // Kick
                this.player2.setKicking(1);
            }
        }*/

        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1 - Nakov
        if (keyCode == KeyEvent.VK_D) {
            //Go right
            this.player1.setMovingRight(false);
        }
        if (keyCode == KeyEvent.VK_A) {
            //Go left
            this.player1.setMovingLeft(false);
        }

        // Player 2 - Prof
        if (keyCode == KeyEvent.VK_RIGHT) {
            //Go right
            this.player2.setMovingRight(false);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            //Go left
            this.player2.setMovingLeft(false);
        }

        //---Test---Do not delete

        if (keyCode == KeyEvent.VK_K && player2.isCanHit()) {
            if (this.player2.getKicking() == 0) {
                // Punch
                this.player2.setPunching(1);
            }
        } else if (keyCode == KeyEvent.VK_L && player2.isCanHit()) {
            if (this.player2.getPunching() == 0) {
                // Kick
                this.player2.setKicking(1);
            }
        }
    }
}
