package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void tileCheck(Entity entity) {
        int solidAreaEntityLeftWorldX = entity.worldX + entity.solidArea.x;
        int solidAreaEntityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int solidAreaEntityTopWorldY = entity.worldY + entity.solidArea.y;
        int solidAreaEntityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = solidAreaEntityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = solidAreaEntityRightWorldX/gamePanel.tileSize;
        int entityTopRow = solidAreaEntityTopWorldY/gamePanel.tileSize;
        int entityBottomRow = solidAreaEntityBottomWorldY/gamePanel.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {
            case "up": {
                entityTopRow = (solidAreaEntityTopWorldY - entity.speed)/ gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "down": {
                entityBottomRow = (solidAreaEntityBottomWorldY + entity.speed)/ gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "left": {
                entityLeftCol = (solidAreaEntityLeftWorldX - entity.speed)/ gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }
            case "right": {
                entityBottomRow = (solidAreaEntityRightWorldX + entity.speed)/ gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            }

        }

    }
}
