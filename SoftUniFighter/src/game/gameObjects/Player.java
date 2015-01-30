package game.gameObjects;

import javax.xml.transform.sax.SAXSource;
import java.awt.*;
import java.awt.image.BufferedImage;

// All the player's data goes here.
public class Player {
    private int x;
    private int y;
    private int health;
    private int velocity;

    private static final int SPEED = 10;
    private int velX;

    public static boolean movingUp;
    public static boolean movingDown;
    public static boolean movingLeft;
    public static boolean movingRight;

    private BufferedImage playerImage;
    private Rectangle boundingBox;

    public Player(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setHealth(100);
        this.setVelX(0);

        this.boundingBox = new Rectangle(this.x, this.y, 300, 200);

        this.velocity = 2;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
        movingUp = false;
    }
    public void update() {
        this.x += this.velX * Player.SPEED;
        //System.out.println("update - "+x);
    }

    public void render(Graphics g) {
        g.drawImage(this.getPlayerImage(), this.x, this.y, null);
        //System.out.println("render - "+x);
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

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setPlayerImage(BufferedImage playerImage) {
        this.playerImage = playerImage;
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }
}
