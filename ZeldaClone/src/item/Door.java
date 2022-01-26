package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Door extends Item{
	
	GamePanel gp;
	
	public Door(GamePanel gp) {
		this.gp = gp;
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}