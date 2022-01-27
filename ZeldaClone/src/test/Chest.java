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
public class Chest extends Item{
	
	GamePanel gp;
	
	public Chest(GamePanel gp) {
		this.gp = gp;

		name = "Chest";
		collision = true;
		
		image = UtilityTool.loadImage("items", "chest");
	}
	
	public void interact(Player player) {
		gp.ui.displayMessage("Congratualtions", Color.yellow, new Font("Arial", Font.BOLD, 80), gp.screenWidth / 2, gp.screenHeight / 2 - gp.tileSize * 3, 3);
		gp.ui.displayMessage("You got the treasure!", Color.white, new Font("Arial", Font.BOLD, 40), gp.screenWidth / 2, gp.screenHeight / 2 + gp.tileSize * 3 , 3);
		gp.musicManager.stop();
		gp.soundEffectManager.play("fanfare");
		gp.gameThread = null;
	}

}