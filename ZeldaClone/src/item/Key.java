package item;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class Key extends Item{
	
	GamePanel gp;
	
	public Key(GamePanel gp) {
		this.gp = gp;
		name = "Key";
		pickupable = true;
		image = UtilityTool.loadImage("items", "key");
	}
	
	public void interact(Player player) {
		gp.soundEffectManager.play("coin");
		gp.ui.showMessage("You got a key!");
		player.keys++;
		visible = false;
		collision = false;
	}

}
