package item;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class ItemManager {
	
	GamePanel gp;
	public List<Item> items;
	
	public ItemManager(GamePanel gp) {
		this.gp = gp;
		items = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void draw(Graphics2D g2) {
		for (Item item : items) {
			item.draw(g2, gp);
		}
	}
	
	public void setItems() {
		Key key1 = new Key(gp);
		key1.worldX = 23 * gp.tileSize;
		key1.worldY = 7 * gp.tileSize;
		
		Key key2 = new Key(gp);
		key2.worldX = 23 * gp.tileSize;
		key2.worldY = 40 * gp.tileSize;

		Key key3 = new Key(gp);
		key3.worldX = 38 * gp.tileSize;
		key3.worldY = 8 * gp.tileSize;
		
		Door door1 = new Door(gp);
		door1.worldX = 10 * gp.tileSize;
		door1.worldY = 11 * gp.tileSize;
		
		Door door2 = new Door(gp);
		door2.worldX = 8 * gp.tileSize;
		door2.worldY = 28 * gp.tileSize;
		
		Door door3 = new Door(gp);
		door3.worldX = 12 * gp.tileSize;
		door3.worldY = 22 * gp.tileSize;
		
		Chest chest = new Chest(gp);
		chest.worldX = 10 * gp.tileSize;
		chest.worldY = 7 * gp.tileSize;
		
		Boots boots = new Boots(gp);
		boots.worldX = 37 * gp.tileSize;
		boots.worldY = 42 * gp.tileSize;
		
		items.add(door2);
		items.add(key1);
		items.add(key2);
		items.add(key3);
		items.add(door1);
		items.add(door2);
		items.add(door3);
		items.add(chest);
		items.add(boots);
	}
	

}
