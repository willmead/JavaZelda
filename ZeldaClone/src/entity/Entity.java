package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public Map<String, List<BufferedImage>> sprites;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 0;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;	
}
