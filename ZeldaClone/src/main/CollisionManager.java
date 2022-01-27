package main;

import java.awt.Rectangle;

import entity.Entity;
import item.Item;
import tile.Tile;

public class CollisionManager {

	GamePanel gp;
	Rectangle entityRect;
	Rectangle itemRect;
	
	public CollisionManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public boolean checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		Tile tile1 = null;
		Tile tile2 = null;
		
		switch (entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize; 
			tile1 = gp.tileManager.getTile(entityTopRow, entityLeftCol);
			tile2 = gp.tileManager.getTile(entityTopRow, entityRightCol);
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize; 
			tile1 = gp.tileManager.getTile(entityBottomRow, entityLeftCol);
			tile2 = gp.tileManager.getTile(entityBottomRow, entityRightCol);
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize; 
			tile1 = gp.tileManager.getTile(entityTopRow, entityLeftCol);
			tile2 = gp.tileManager.getTile(entityBottomRow, entityLeftCol);
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize; 
			tile1 = gp.tileManager.getTile(entityTopRow, entityRightCol);
			tile2 = gp.tileManager.getTile(entityBottomRow, entityRightCol);
			break;
		}
		if (tile1 != null && tile2 != null && (tile1.collision || tile2.collision)) {
			return true;
		}
		return false;
	}
	
	public Item checkObject(Entity entity, boolean player) {
		Item collisionItem = null;
		entityRect = new Rectangle(entity.solidArea.x + entity.worldX, entity.solidArea.y + entity.worldY, 46, 46);
		
		for (Item item : gp.itemManager.items) {
			itemRect = new Rectangle(item.solidArea.x + item.worldX, item.solidArea.y + item.worldY, 46, 46);
			
			switch (entity.direction) {
			case "up":
				entityRect.y -= entity.speed;
				break;
			case "down":
				entityRect.y += entity.speed;
				break;
			case "left":
				entityRect.x -= entity.speed;
				break;
			case "right":
				entityRect.x += entity.speed;
				break;
			}
			
			if (entityRect.intersects(itemRect)) {
				entity.collisionOn = item.collision;
				if (player) {
					collisionItem = item;
				}
			}
		}		
		return collisionItem;
	}
	
}
