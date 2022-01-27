package test;

import java.awt.Color;
import java.awt.Font;

import entity.Player;
import item.Item;
import main.GamePanel;
import main.UtilityTool;

/**
* This is a demo item.
* Use this as a template for creating your own items!
*/
public class Key extends Item{
	
	GamePanel gp;
	
	public Key(GamePanel gp) {
		this.gp = gp;
		name = "Key";
		image = UtilityTool.loadImage("items", "key");
	}
	
	public void interact(Player player) {
		gp.soundEffectManager.play("coin");
		gp.ui.displayMessage("You got a key!", 
				Color.white, 
				new Font("Arial", Font.BOLD, 80), 
				gp.screenWidth / 2, 
				gp.screenHeight / 2, 
				3);
		player.keys++;
		gp.itemManager.removeItem(this);
	}

}
