package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	public int standCounter = 0;
	boolean moving = false;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
		screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 46;
		solidArea.height = 46;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
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
			e.printStackTrace();
		}
		
		up1 = setup("boy_up_1");
		up2 = setup("boy_up_2");
		down1 = setup("boy_down_1");
		down2 = setup("boy_down_2");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}
	
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void update() {
		
		if (!moving) {
			if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
				
				if (keyH.upPressed) {
					direction = "up";
				}
				else if (keyH.downPressed) {
					direction = "down";
				}
				else if (keyH.leftPressed) {
					direction = "left";
				}
				else if (keyH.rightPressed) {
					direction = "right";
				}
				
				moving = true;
				
				// CHECK TILE COLLISION
				collisionOn = false;
				gp.collisionChecker.checkTile(this);
				
				int objIndex = gp.collisionChecker.checkObject(this, true);
				pickUpObject(objIndex);
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
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
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
			spriteCounter++;
			
			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			pixelCounter += speed;
			
			if (pixelCounter == 48) {
				moving = false;
				pixelCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.itemManager.items.get(i).name;
			
			switch(objectName) {
			case "Key":
				gp.soundEffectManager.setFile(1);
				gp.soundEffectManager.play();
				hasKey++;
				gp.itemManager.items.remove(i);
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				if (hasKey > 0) {
					gp.soundEffectManager.setFile(3);
					gp.soundEffectManager.play();
					gp.itemManager.items.remove(i);
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				} else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				gp.soundEffectManager.setFile(2);
				gp.soundEffectManager.play();
				speed += 2;
				gp.itemManager.items.remove(i);
				gp.ui.showMessage("Speed Up!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.musicManager.stop();
				gp.soundEffectManager.setFile(4);
				gp.soundEffectManager.play();
				break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;	
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;	
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;	
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;	
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}

}
