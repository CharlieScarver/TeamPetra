package game.gameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

// All the player's data goes here.
public class Player {
    private static final int NakovXOffset = 70;
    private static final int NakovReversedXOffset = 100;
    private static final int ProfXOffset = 130;
    private static final int ProfReversedXOffset = 50;
    private static final int YOffset = 40;
    private static final int WALK_ANIMATION_LENGTH = 13;
    private static final int PUNCH_ANIMATION_LENGTH = 10;
    private static final int KICK_ANIMATION_LENGTH = 18;
    private static final int AFTER_ATTACK_DELAY = 30;

    private int x;
    private int y;
    private int width, height;
    private int health;
    private int velocity;

    private int walkState;
    private boolean printWalkImg1;

    private boolean movingLeft;
    private boolean movingRight;

    private boolean isBlocking;
    private boolean canHit;
    private int punching;
    private int kicking;

    private boolean keyReleased;
    private boolean isReversed;

    private CharacterEnum identity;

    private BufferedImage playerStationary;
    private BufferedImage playerWalk;
    private BufferedImage playerPunch;
    private BufferedImage playerKick;
    private BufferedImage playerBlock;
    private BufferedImage reversePlayerStationary;
    private BufferedImage reversePlayerWalk;
    private BufferedImage reversePlayerPunch;
    private BufferedImage reversePlayerKick;
    private BufferedImage reversePlayerBlock;

    private BufferedImage playerCurrentStationary;
    private BufferedImage playerCurrentWalk;
    private BufferedImage playerCurrentPunch;
    private BufferedImage playerCurrentKick;
    private BufferedImage playerCurrentBlock;

    //private Timer timer;

    private Rectangle boundingBox;

    public int counter = 1;

    public Player(int x, int y, CharacterEnum id) {
        this.x = x;
        this.y = y;
        this.setHealth(100);

        this.identity = id;
        this.walkState = 1;
        this.printWalkImg1 = false;

        this.width = 250;
        this.height = 420;

        this.boundingBox = new Rectangle(this.width, this.height);

        this.velocity = 4;
        this.setMovingLeft(false);
        this.setMovingRight(false);
        this.setCanHit(true);
        this.setBlocking(false);
        this.setPunching(0);
        this.setKicking(0);

        this.setKeyReleased(true);

        //this.timer = new Timer();
    }

    public void update() {

        if (this.identity.equals(CharacterEnum.Nakov)) {
            if (!this.isReversed) {
                this.boundingBox.setBounds(this.x + NakovXOffset, this.y + YOffset, this.width, this.height);
            } else {
                this.boundingBox.setBounds(this.x + NakovReversedXOffset, this.y + YOffset, this.width, this.height);
            }
        } else {
            if (!this.isReversed) {
                this.boundingBox.setBounds(this.x + ProfXOffset, this.y + YOffset, this.width, this.height);
            } else {
                this.boundingBox.setBounds(this.x + ProfReversedXOffset, this.y + YOffset, this.width, this.height);
            }
        }

        // Moving the characters
        if(this.movingLeft) {
            this.x -= this.velocity;
        }
        if(this.movingRight) {
            this.x += this.velocity;
        }

        // If a character punches or kicks he will move forward
        if ((this.getPunching() > 0 || this.getKicking() > 0) &&
                ((this.identity.equals(CharacterEnum.Nakov) && !this.isReversed) ||
                (this.identity.equals(CharacterEnum.Prof)  && this.isReversed))) {
            this.x += this.velocity/4;
            this.boundingBox.setBounds(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width + 40, this.boundingBox.height);

        } else if ((this.getPunching() > 0 || this.getKicking() > 0) &&
                ((this.identity.equals(CharacterEnum.Prof) && !this.isReversed) ||
                (this.identity.equals(CharacterEnum.Nakov) && this.isReversed))) {
            this.x -= this.velocity/4;
            this.boundingBox.setBounds(this.boundingBox.x - 40, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);

        }

        // Characters can't leave the border
        if (this.x < -120) {
            this.x = -120;
        } else if (this.x > 700) {
            this.x = 700;
        }
    }

    public void render(Graphics g) {
        if (this.isBlocking()) {
            g.drawImage(this.playerCurrentBlock, this.x, this.y, null);
        } else {
            if (!(this.getPunching() > 0) && !(this.getKicking() > 0)) {
                // Walking animation
                if (this.isMovingLeft() || this.isMovingRight()) {
                    if (this.walkState % WALK_ANIMATION_LENGTH == 0) {
                        if (this.printWalkImg1) {
                            this.printWalkImg1 = false;
                        } else {
                            this.printWalkImg1 = true;
                        }
                        this.walkState = 1;
                    }

                    if (this.printWalkImg1) {
                        g.drawImage(this.playerCurrentWalk, this.x, this.y, null);
                    } else {
                        g.drawImage(this.playerCurrentStationary, this.x, this.y, null);
                    }
                    this.walkState++;
                } else {
                    g.drawImage(this.playerCurrentStationary, this.x, this.y, null);
                    this.walkState = 1;
                }

            } else if (this.getPunching() > 0 && this.getKicking() == 0) {
                // Punching animation
                g.drawImage(this.playerCurrentPunch, this.x, this.y, null);
                this.punching++;

                if (this.punching > PUNCH_ANIMATION_LENGTH) {
                    this.setPunching(0);
                }

            } else if (this.getKicking() > 0 && this.getPunching() == 0) {
                // Kicking animation
                g.drawImage(this.playerCurrentKick, this.x, this.y, null);
                this.kicking++;

                if (this.getKicking() > KICK_ANIMATION_LENGTH) {
                    this.setKicking(0);
                }
            } else {
                //Standing still
                g.drawImage(this.playerCurrentStationary, this.x, this.y, null);
                this.walkState = 1;
            }
        }

/*      // Doesn't work as expected
        if (!this.isCanHit()) {
            timer.purge();
            this.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isKeyReleased()) {
                        setCanHit(true);
                    }
                }
            }, 2000);
        }*/

        // After-attack delay
        if (!this.isCanHit()) {
            if (isKeyReleased()) {
                counter++;

                if (counter == AFTER_ATTACK_DELAY) {
                    setCanHit(true);
                    counter = 1;
                }
            }
        }

/*      // Test draw of the bounding boxes
        g.setColor(Color.red);
        if (this.identity.equals(CharacterEnum.Nakov)) {
            //System.out.println(this.identity + " " + this.canHit + " " + this.keyReleased);
            g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        } else {
            g.drawRect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        }*/

    }

    public boolean intersects(Player other) {
        if (other.boundingBox.intersects(this.boundingBox) || this.boundingBox.intersects(other.boundingBox)) {
            return true;
        }
        return false;
    }

    public void pushBack() {
        if ((this.identity == CharacterEnum.Prof && !this.isReversed) || (this.identity == CharacterEnum.Nakov && this.isReversed)) {
            this.x += this.velocity * 5;
            this.setCanHit(false);
        } else if ((this.identity == CharacterEnum.Nakov && !this.isReversed) || (this.identity == CharacterEnum.Prof && this.isReversed)){
            this.x -= this.velocity * 5;
            this.setCanHit(false);
        }
    }

    public void checkReverse(Player other) {
        if (this.identity == CharacterEnum.Nakov) {
            if (this.x > other.getX()) {
                this.isReversed = true;
            } else {
                this.isReversed = false;
            }
        } else {
            if (this.x < other.getX()) {
                this.isReversed = true;
            } else {
                this.isReversed = false;
            }
        }

        if (isReversed) {
            this.playerCurrentStationary = this.reversePlayerStationary;
            this.playerCurrentWalk = this.reversePlayerWalk;
            this.playerCurrentKick = this.reversePlayerKick;
            this.playerCurrentPunch = this.reversePlayerPunch;
            this.playerCurrentBlock = this.reversePlayerBlock;
        } else {
            this.playerCurrentStationary = this.playerStationary;
            this.playerCurrentWalk = this.playerWalk;
            this.playerCurrentPunch = this.playerPunch;
            this.playerCurrentKick = this.playerKick;
            this.playerCurrentBlock = this.playerBlock;
        }
    }

    public void getHit (Integer healthLost) {
        this.setHealth(this.getHealth() - healthLost);
        this.pushBack();
    }

    public int getX() {
        return x;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public CharacterEnum getIdentity() {
        return identity;
    }

    public boolean isCanHit() {
        return canHit;
    }

    public void setCanHit(boolean canHit) {
        this.canHit = canHit;
    }

    public boolean isKeyReleased() {
        return keyReleased;
    }

    public void setKeyReleased(boolean keyReleased) {
        this.keyReleased = keyReleased;
    }

    public void setPlayerStationary(BufferedImage playerStationary) {
        this.playerStationary = playerStationary;
    }

    public void setPlayerWalk(BufferedImage playerWalk) {
        this.playerWalk = playerWalk;
    }

    public void setPlayerPunch(BufferedImage playerPunch) {
        this.playerPunch = playerPunch;
    }

    public void setPlayerKick(BufferedImage playerKick) {
        this.playerKick = playerKick;
    }

    public void setReversePlayerStationary(BufferedImage reversePlayerStationary) {
        this.reversePlayerStationary = reversePlayerStationary;
    }

    public void setReversePlayerWalk(BufferedImage reversePlayerWalk) {
        this.reversePlayerWalk = reversePlayerWalk;
    }

    public void setReversePlayerPunch(BufferedImage reversePlayerPunch) {
        this.reversePlayerPunch = reversePlayerPunch;
    }

    public void setReversePlayerKick(BufferedImage reversePlayerKick) {
        this.reversePlayerKick = reversePlayerKick;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean isBlocking) {
        this.isBlocking = isBlocking;
    }

    public void setPlayerBlock(BufferedImage playerBlock) {
        this.playerBlock = playerBlock;
    }

    public void setReversePlayerBlock(BufferedImage reversePlayerBlock) {
        this.reversePlayerBlock = reversePlayerBlock;
    }
}
