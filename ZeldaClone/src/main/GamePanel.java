package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import entity.Player;
import item.Item;
import item.ItemManager;
import tile.TileManager;
import ui.UI;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 Tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 48x48 Tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768px
	public final int screenHeight = tileSize * maxScreenRow; // 576px
	
	// FPS
	int FPS = 60;
	double drawInterval = 1000000000 / FPS;
	double delta = 0;

	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// MANAGERS
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler();
	public SoundManager musicManager = new SoundManager();
	public SoundManager soundEffectManager = new SoundManager();
	public CollisionManager collisionChecker = new CollisionManager(this);
	public ItemManager itemManager = new ItemManager(this);
	public UI ui = new UI(this);
	
	// GAME THREAD
	public Thread gameThread;

	// PLAYER AND ITEMS
	public Player player = new Player(this, keyHandler);
	public List<Item> items = new ArrayList<Item>();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
//		itemManager.loadDemoItems();
//		tileManager.loadDemoTileset();
//		tileManager.loadMap("/maps/map01.txt");
//		musicManager.loadDemoSounds();
//		musicManager.play("soundtrack");
//		musicManager.loop();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		tileManager.draw(g2);		
		itemManager.draw(g2);
		player.draw(g2);
		ui.draw(g2);
		
		g2.dispose();
	}
	

}
