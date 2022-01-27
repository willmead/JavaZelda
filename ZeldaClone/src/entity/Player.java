package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import item.Item;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	boolean moving = false;
	int pixelCounter = 0;
	int standCounter = 0;

	// INVENTORY
	public int keys = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
		screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		
		solidArea = new Rectangle(1, 1, 46, 46);
		
		speed = 4;
		direction = "down";
				
		// SPRITES
		sprites = new HashMap<String, List<BufferedImage>>();
		
		List<BufferedImage> up = new ArrayList<BufferedImage>();
		up.add(UtilityTool.loadImage("player", "boy_up_1"));
		up.add(UtilityTool.loadImage("player", "boy_up_2"));
		
		List<BufferedImage> down = new ArrayList<BufferedImage>();
		down.add(UtilityTool.loadImage("player", "boy_down_1"));
		down.add(UtilityTool.loadImage("player", "boy_down_2"));

		List<BufferedImage> left = new ArrayList<BufferedImage>();
		left.add(UtilityTool.loadImage("player", "boy_left_1"));
		left.add(UtilityTool.loadImage("player", "boy_left_2"));
		
		List<BufferedImage> right = new ArrayList<BufferedImage>();
		right.add(UtilityTool.loadImage("player", "boy_right_1"));
		right.add(UtilityTool.loadImage("player", "boy_right_2"));

		sprites.put("up", up);
		sprites.put("down", down);
		sprites.put("left", left);
		sprites.put("right", right);
	}

	
	public void update() {
		
		if (!moving) {
			if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
				if 		(keyH.upPressed) 	{ direction = "up"; }
				else if (keyH.downPressed) 	{ direction = "down"; }
				else if (keyH.leftPressed) 	{ direction = "left"; }
				else if (keyH.rightPressed) { direction = "right"; }
				
				moving = true;
				collisionOn = gp.collisionChecker.checkTile(this);
				Item collisionItem = gp.collisionChecker.checkObject(this, true);
				if (collisionItem != null && collisionItem.visible) {
					collisionItem.interact(this);
				}
				
			}
			else {
				standCounter++;
				if (standCounter == 20) {
					spriteNum = 1;
					standCounter = 0;
				}
			}
		}
			
		if(moving) {
			if (!collisionOn) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			// UPDATE SPRITE ANIMATION
			spriteCounter++;
			if (spriteCounter > 10) {
				spriteNum = 1 - spriteNum;
				spriteCounter = 0;
			}
			
			// TILE BASED MOVEMENT
			pixelCounter += speed;
			if (pixelCounter == 48) {
				moving = false;
				pixelCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = sprites.get(direction).get(spriteNum);
		g2.drawImage(image, screenX, screenY, null);
	}

}
