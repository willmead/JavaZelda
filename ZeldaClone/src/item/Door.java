package item;

import java.awt.Color;
import java.awt.Font;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

/**
* This is a demo item.
* Use this as a template for creating your own items!
*/
public class Door extends Item {
	
	GamePanel gp;
	
	public Door(GamePanel gp) {
		this.gp = gp;
		name = "Door";
		collision = true;
		
		image = UtilityTool.loadImage("items", "door");
	}
	
	public void interact(Player player) {
		if (player.keys > 0) {
			gp.soundEffectManager.play("unlock");
			gp.ui.displayMessage("You opened the door!", 
					Color.white, 
					new Font("Arial", Font.BOLD, 80), 
					gp.screenWidth / 2, 
					gp.screenHeight / 2, 
					3);
			visible = false;
			collision = false;
			player.keys--;
		} else {
			gp.ui.displayMessage("You need a key!", 
					Color.white, 
					new Font("Arial", Font.BOLD, 80), 
					gp.screenWidth / 2, 
					gp.screenHeight / 2, 
					3);
		}
	}

}