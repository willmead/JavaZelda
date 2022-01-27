package item;

import entity.Player;
import main.GamePanel;
import main.UtilityTool;

public class Chest extends Item{
	
	GamePanel gp;
	
	public Chest(GamePanel gp) {
		this.gp = gp;

		name = "Chest";
		collision = true;
		
		image = UtilityTool.loadImage("items", "chest");
	}
	
	public void interact(Player player) {
		gp.ui.gameFinished = true;
		gp.musicManager.stop();
		gp.soundEffectManager.play("fanfare");
	}

}