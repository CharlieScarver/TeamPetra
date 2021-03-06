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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1 - Nakov
        if (!this.player1.isBlocking()) {
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

            if (keyCode == KeyEvent.VK_W) {
                this.player1.setBlocking(true);
                this.player1.setMovingLeft(false);
                this.player1.setMovingRight(false);

                this.player1.setPunching(0);
                this.player1.setKicking(0);
                this.player1.setKeyReleased(false);
            }

            if (keyCode == KeyEvent.VK_F) {
                if (this.player1.getKicking() == 0 && this.player1.isCanHit() && this.player1.isKeyReleased()) {
                    // Punch
                    this.player1.setPunching(1);
                    // Punch key pressed (hold)
                    this.player1.setKeyReleased(false);
                    // Can't hit again for now
                    this.player1.setCanHit(false);
                }
            } else if (keyCode == KeyEvent.VK_G) {
                if (this.player1.getPunching() == 0 && this.player1.isCanHit() && this.player1.isKeyReleased()) {
                    // Kick
                    this.player1.setKicking(1);
                    // Kick key pressed (hold)
                    this.player1.setKeyReleased(false);
                    // Can't hit again for now
                    this.player1.setCanHit(false);
                }
            }
        }


        if (!this.player2.isBlocking()) {
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

            if (keyCode == KeyEvent.VK_UP) {
                this.player2.setBlocking(true);
                this.player2.setMovingLeft(false);
                this.player2.setMovingRight(false);

                this.player2.setPunching(0);
                this.player2.setKicking(0);
                this.player2.setKeyReleased(false);
            }

            if (keyCode == KeyEvent.VK_K) {
                if (this.player2.getKicking() == 0 && this.player2.isCanHit() && this.player2.isKeyReleased()) {
                    // Punch
                    this.player2.setPunching(1);
                    // Punch key pressed (hold)
                    this.player2.setKeyReleased(false);
                    // Can't hit again for now
                    this.player2.setCanHit(false);
                }
            } else if (keyCode == KeyEvent.VK_L) {
                if (this.player2.getPunching() == 0 && this.player2.isCanHit() && this.player2.isKeyReleased()) {
                    // Kick
                    this.player2.setKicking(1);
                    // Kick key pressed (hold)
                    this.player2.setKeyReleased(false);
                    // Can't hit again for now
                    this.player2.setCanHit(false);
                }
            }
        }

        // Exit game
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

        if (keyCode == KeyEvent.VK_W) {
            this.player1.setBlocking(false);
            this.player1.setKeyReleased(true);
        }

        if (keyCode == KeyEvent.VK_F) {
            // Punch key was released
            this.player1.setKeyReleased(true);
        } else if (keyCode == KeyEvent.VK_G) {
            // Kick key was released
            this.player1.setKeyReleased(true);
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

        if (keyCode == KeyEvent.VK_UP) {
            this.player2.setBlocking(false);
            this.player2.setKeyReleased(true);
        }

        if (keyCode == KeyEvent.VK_K) {
            // Punch key was released
            this.player2.setKeyReleased(true);
        } else if (keyCode == KeyEvent.VK_L) {
            // Kick key was released
            this.player2.setKeyReleased(true);
        }

    }
}
