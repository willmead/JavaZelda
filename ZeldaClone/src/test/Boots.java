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
public class Boots extends Item{
	
	GamePanel gp;
	
	public Boots(GamePanel gp) {
		this.gp = gp;
		name = "Boots";
		image = UtilityTool.loadImage("items", "boots");
	}
	
	public void interact(Player player) {
		gp.soundEffectManager.play("powerup");
		player.speed += 2;
		gp.ui.displayMessage("Speed Up!", 
				Color.white, 
				new Font("Arial", Font.BOLD, 80), 
				gp.screenWidth / 2, 
				gp.screenHeight / 2, 
				3);
		gp.itemManager.removeItem(this);
	}

}