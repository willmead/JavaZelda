package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Boots extends Item{
	
	GamePanel gp;
	
	public Boots(GamePanel gp) {
		this.gp = gp;

		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}