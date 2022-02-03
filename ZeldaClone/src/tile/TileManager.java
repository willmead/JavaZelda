package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
	public void addTile(Tile tile) {
		tileset.add(tile);
	}
	
	public void createAndAddTile(int index, String imageName, boolean collision) {
		BufferedImage image = UtilityTool.loadImage("tiles", imageName, gp.tileSize, gp.tileSize);
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
		createAndAddTile(0, "grass00", false);
		createAndAddTile(1, "grass00", false);
		createAndAddTile(2, "grass00", false);
		createAndAddTile(3, "grass00", false);
		createAndAddTile(4, "grass00", false);
		createAndAddTile(5, "grass00", false);
		createAndAddTile(6, "grass00", false);
		createAndAddTile(7, "grass00", false);
		createAndAddTile(8, "grass00", false);
		createAndAddTile(9, "grass00", false);

		createAndAddTile(10, "grass00", false);
		createAndAddTile(11, "grass01", false);
		createAndAddTile(12, "water00", true);
		createAndAddTile(13, "water01", true);
		createAndAddTile(14, "water02", true);
		createAndAddTile(15, "water03", true);
		createAndAddTile(16, "water04", true);
		createAndAddTile(17, "water05", true);
		createAndAddTile(18, "water06", true);
		createAndAddTile(19, "water07", true);
		createAndAddTile(20, "water08", true);
		createAndAddTile(21, "water09", true);
		createAndAddTile(22, "water10", true);
		createAndAddTile(23, "water11", true);
		createAndAddTile(24, "water12", true);
		createAndAddTile(25, "water13", true);
		createAndAddTile(26, "road00", false);
		createAndAddTile(27, "road01", false);
		createAndAddTile(28, "road02", false);
		createAndAddTile(29, "road03", false);
		createAndAddTile(30, "road04", false);
		createAndAddTile(31, "road05", false);
		createAndAddTile(32, "road06", false);
		createAndAddTile(33, "road07", false);
		createAndAddTile(34, "road08", false);
		createAndAddTile(35, "road09", false);
		createAndAddTile(36, "road10", false);
		createAndAddTile(37, "road11", false);
		createAndAddTile(38, "road12", false);
		createAndAddTile(39, "earth", false);
		createAndAddTile(40, "wall", true);
		createAndAddTile(41, "tree", true);
	}
	
	public void loadMap(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			
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
	
	
	public void loadDemoMap(String filePath) {
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
