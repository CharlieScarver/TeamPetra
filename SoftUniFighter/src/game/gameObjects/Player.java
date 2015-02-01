package game.gameObjects;

import gfx.Assets;
import gfx.ImageLoader;

import javax.xml.transform.sax.SAXSource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

// All the player's data goes here.
public class Player {
    private int x;
    private int y;
    private int width, height;
    private int health;
    private int velocity;

    private int walkState;
    private boolean printWalkImg1;

    public boolean movingUp;
    public boolean movingDown;
    public boolean movingLeft;
    public boolean movingRight;
    public int punching;
    public int kicking;

    private String identity;

    private BufferedImage playerWalk1, playerWalk2;
    private BufferedImage playerPunch, playerKick;
    private Rectangle boundingBox;

    public Player(int x, int y, String id, String pathWalk1, String pathWalk2, String pathPunch, String pathKick) {
        this.setX(x);
        this.setY(y);
        this.setHealth(100);

        this.identity = id;
        this.walkState = 1;
        this.printWalkImg1 = false;

        this.width = 300;
        this.height = 200;

        this.boundingBox = new Rectangle(this.width, this.height);

        this.velocity = 4;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
        movingUp = false;
        punching = 0;
        kicking = 0;

        playerWalk1 = ImageLoader.loadImage(pathWalk1);
        playerWalk2 = ImageLoader.loadImage(pathWalk2);
        playerPunch = ImageLoader.loadImage(pathPunch);
        playerKick = ImageLoader.loadImage(pathKick);
    }

    public void update() {
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if(movingLeft) {
            this.x -= this.velocity;
        }
        if(movingRight) {
            this.x += this.velocity;
        }

        if ((punching > 0 || kicking > 0) && identity.equals("Nakov")) {
            this.x += this.velocity/2;
        } else if ((punching > 0 || kicking > 0) && identity.equals("Prof")) {
            this.x -= this.velocity/2;
        }

        if (this.x < -120) {
            this.x = -120;
        } else if (this.x > 700) {
            this.x = 700;
        }
    }

    public void render(Graphics g) {
        if (!(punching > 0) && !(kicking > 0)) {
            if (movingLeft || movingRight) {
                if (walkState % 5 == 0) {
                    if (printWalkImg1) {
                        printWalkImg1 = false;
                    } else {
                        printWalkImg1 = true;
                    }
                    walkState = 1;
                }

                if (printWalkImg1) {
                    g.drawImage(this.playerWalk2, this.x, this.y, null);
                } else {
                    g.drawImage(this.playerWalk1, this.x, this.y, null);
                }
                walkState++;
            } else {
                g.drawImage(this.playerWalk1, this.x, this.y, null);
                walkState = 1;
            }
        } else if (punching > 0) {
            g.drawImage(this.playerPunch, this.x, this.y, null);
            punching++;
            if (punching > 8) {
                punching = 0;
            }
        } else if (kicking > 0 && punching == 0) {
            g.drawImage(this.playerKick, this.x, this.y, null);
            kicking++;
            if (kicking > 8) {
                kicking = 0;
            }
        } else {
            g.drawImage(this.playerWalk1, this.x, this.y, null);
            walkState = 1;
        }

    }

    public boolean intersects(Rectangle r) {
        if (r.contains(this.boundingBox) || this.boundingBox.contains(r)) {
            return true;
        }

        return false;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
