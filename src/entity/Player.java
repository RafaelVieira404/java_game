package entity;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyBoardHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyBoardHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyBoardHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

        screenX = gp.screenHeight/2 - (gp.tileSize/2);
        screenY = gp.screenWidth/2 - (gp.tileSize/2);

        // Use this to set the size of collision area the player will have, have in mind the size of the tile you are using
        solidArea = new Rectangle();
        solidArea.x = 8; // index start of the rectangle in x
        solidArea.y = 16; // index start of the rectangle in y
        solidArea.height = 32; // the size of the rectangle
        solidArea.width = 32;


    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 30 ;
        worldY = gp.tileSize * 30;
        speed = 4;
        direction = "down";
    }

    public void update() {

       if (keyH.pgUpKeyPressed.equals(true) || keyH.pgDownKeyPressed.equals(true) || keyH.leftKeyPressed.equals(true) || keyH.rightKeyPressed.equals(true)) {

           if (keyH.pgUpKeyPressed) {
               direction = "up";
           } else if (keyH.pgDownKeyPressed) {
               direction = "down";
           } else if (keyH.leftKeyPressed) {
               direction = "left";
           } else if (keyH.rightKeyPressed) {
               direction = "right";
           }
           // checkCollision tileCollision
           collisionOn = false;
           gp.collisionChecker.tileCheck(this);

           // IF collision false player can move

           if (!collisionOn) {
               switch (direction) {
                   case "up": {
                       worldY -= speed;
                       break;
                   }

                   case "down": {
                       worldY += speed;
                       break;
                   }

                   case "left": {
                       worldX -= speed;
                       break;
                   }

                   case "right": {
                       worldX += speed;
                       break;
                   }
               }
           }

           sprintCounter += 1;
           if (sprintCounter > 12) {
               if (sprintNum == 1) {
                   sprintNum = 2;
               } else if (sprintNum == 2) {
                   sprintNum = 1;
               }
               sprintCounter = 0;
           }
       }
    }
    public void draw(Graphics2D g2) {
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        if (direction.equals("up")) {
            if (sprintNum == 1) {
                image = up1;
            } else if (sprintNum == 2) {
                image = up2;
            }
        } else if (direction.equals("down")) {
            if (sprintNum == 1) {
                image = down1;
            } else if (sprintNum == 2) {
                image = down2;
            }
        } else if (direction.equals("left")) {
            if (sprintNum == 1) {
                image = left1;
            } else if (sprintNum == 2) {
                image = left2;
            }
        } else if (direction.equals("right")) {
            if (sprintNum == 1) {
                image = right1;
            } else if (sprintNum == 2) {
                image = right2;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
