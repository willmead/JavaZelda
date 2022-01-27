package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public List<Tile> tileset; 
	public List<ArrayList<Tile>> worldMap; 
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tileset = new ArrayList<Tile>();
		worldMap = new ArrayList<ArrayList<Tile>>();	
	}
	
	public void addTile(int index, String imageName, boolean collision) {
		BufferedImage image = UtilityTool.loadImage("tiles", imageName);
		tileset.add(index, new Tile(image, collision));
	}
	
	public Tile getTile(int row, int col) {
		if (row >= 0 && row <= worldMap.size()) {
			if (col >= 0 && col <= worldMap.get(row).size()) {
				return worldMap.get(row).get(col);
			}
		}
		return null;
	}
	
	public void loadDemoTileset() {
		addTile(0, "grass00", false);
		addTile(1, "grass00", false);
		addTile(2, "grass00", false);
		addTile(3, "grass00", false);
		addTile(4, "grass00", false);
		addTile(5, "grass00", false);
		addTile(6, "grass00", false);
		addTile(7, "grass00", false);
		addTile(8, "grass00", false);
		addTile(9, "grass00", false);

		addTile(10, "grass00", false);
		addTile(11, "grass01", false);
		addTile(12, "water00", true);
		addTile(13, "water01", true);
		addTile(14, "water02", true);
		addTile(15, "water03", true);
		addTile(16, "water04", true);
		addTile(17, "water05", true);
		addTile(18, "water06", true);
		addTile(19, "water07", true);
		addTile(20, "water08", true);
		addTile(21, "water09", true);
		addTile(22, "water10", true);
		addTile(23, "water11", true);
		addTile(24, "water12", true);
		addTile(25, "water13", true);
		addTile(26, "road00", false);
		addTile(27, "road01", false);
		addTile(28, "road02", false);
		addTile(29, "road03", false);
		addTile(30, "road04", false);
		addTile(31, "road05", false);
		addTile(32, "road06", false);
		addTile(33, "road07", false);
		addTile(34, "road08", false);
		addTile(35, "road09", false);
		addTile(36, "road10", false);
		addTile(37, "road11", false);
		addTile(38, "road12", false);
		addTile(39, "earth", false);
		addTile(40, "wall", true);
		addTile(41, "tree", true);
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			for (int row = 0; row < gp.maxWorldRow; row++) {
				worldMap.add(new ArrayList<Tile>());
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for (int col = 0; col < gp.maxWorldCol; col++) {
					int num = Integer.parseInt(numbers[col]);
					worldMap.get(row).add(col, tileset.get(num));
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		for (int row = 0; row < worldMap.size(); row++) {
			for (int col = 0; col < worldMap.get(row).size(); col++) {
				int worldX = col * gp.tileSize;
				int worldY = row * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
								
				if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
						g2.drawImage(worldMap.get(row).get(col).image, screenX, screenY, null);
					}
			}
		}
	}
}
