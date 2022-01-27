package item;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class Door extends Item{
	
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
			gp.ui.showMessage("You opened the door!");
			visible = false;
			collision = false;
			player.keys--;
		} else {
			gp.ui.showMessage("You need a key!");
		}
	}

}