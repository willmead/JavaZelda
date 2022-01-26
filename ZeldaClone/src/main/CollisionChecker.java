package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
			
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize; 
			tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			
			if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for (int i = 0; i < gp.itemManager.items.size(); i++) {
			if (gp.itemManager.items.get(i) != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.itemManager.items.get(i).solidArea.x = gp.itemManager.items.get(i).worldX + gp.itemManager.items.get(i).solidArea.x;
				gp.itemManager.items.get(i).solidArea.y = gp.itemManager.items.get(i).worldY + gp.itemManager.items.get(i).solidArea.y;
				
				switch (entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					
					if (entity.solidArea.intersects(gp.itemManager.items.get(i).solidArea)) {
						if (gp.itemManager.items.get(i).collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gp.itemManager.items.get(i).solidArea)) {
						if (gp.itemManager.items.get(i).collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gp.itemManager.items.get(i).solidArea)) {
						if (gp.itemManager.items.get(i).collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gp.itemManager.items.get(i).solidArea)) {
						if (gp.itemManager.items.get(i).collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.itemManager.items.get(i).solidArea.x = gp.itemManager.items.get(i).solidAreaDefaultX;
				gp.itemManager.items.get(i).solidArea.y = gp.itemManager.items.get(i).solidAreaDefaultY;
			}
			

		}
		return index;
	}
	
}
