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

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;
    private int punching;
    private int kicking;

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
        this.movingDown = false;
        this.movingLeft = false;
        this.movingRight = false;
        this.movingUp = false;
        this.punching = 0;
        this.kicking = 0;

        this.playerWalk1 = ImageLoader.loadImage(pathWalk1);
        this.playerWalk2 = ImageLoader.loadImage(pathWalk2);
        this.playerPunch = ImageLoader.loadImage(pathPunch);
        this.playerKick = ImageLoader.loadImage(pathKick);
    }

    public void update() {
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if(this.movingLeft) {
            this.x -= this.velocity;
        }
        if(this.movingRight) {
            this.x += this.velocity;
        }

        if ((this.punching > 0 || this.kicking > 0) && this.identity.equals("Nakov")) {
            this.x += this.velocity/2;
        } else if ((this.punching > 0 || this.kicking > 0) && this.identity.equals("Prof")) {
            this.x -= this.velocity/2;
        }

        if (this.x < -120) {
            this.x = -120;
        } else if (this.x > 700) {
            this.x = 700;
        }
    }

    public void render(Graphics g) {
        if (!(this.punching > 0) && !(this.kicking > 0)) {
            if (this.movingLeft || this.movingRight) {
                if (this.walkState % 5 == 0) {
                    if (this.printWalkImg1) {
                        this.printWalkImg1 = false;
                    } else {
                        this.printWalkImg1 = true;
                    }
                    this.walkState = 1;
                }

                if (this.printWalkImg1) {
                    g.drawImage(this.playerWalk2, this.x, this.y, null);
                } else {
                    g.drawImage(this.playerWalk1, this.x, this.y, null);
                }
                this.walkState++;
            } else {
                g.drawImage(this.playerWalk1, this.x, this.y, null);
                this.walkState = 1;
            }
        } else if (this.punching > 0) {
            g.drawImage(this.playerPunch, this.x, this.y, null);
            this.punching++;
            if (this.punching > 8) {
                this.punching = 0;
            }
        } else if (this.kicking > 0 && this.punching == 0) {
            g.drawImage(this.playerKick, this.x, this.y, null);
            this.kicking++;
            if (this.kicking > 8) {
                this.kicking = 0;
            }
        } else {
            g.drawImage(this.playerWalk1, this.x, this.y, null);
            this.walkState = 1;
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

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public int getPunching() {
        return punching;
    }

    public void setPunching(int punching) {
        this.punching = punching;
    }

    public int getKicking() {
        return kicking;
    }

    public void setKicking(int kicking) {
        this.kicking = kicking;
    }

}
