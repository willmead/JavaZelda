package item;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class Boots extends Item{
	
	GamePanel gp;
	
	public Boots(GamePanel gp) {
		this.gp = gp;

		name = "Boots";
		pickupable = true;
		
		image = UtilityTool.loadImage("items", "boots");
	}
	
	public void interact(Player player) {
		gp.soundEffectManager.play("powerup");
		player.speed += 2;
		gp.ui.showMessage("Speed Up!");
		visible = false;
		collision = false;
	}

}