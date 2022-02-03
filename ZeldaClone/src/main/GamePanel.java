package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import item.ItemManager;
import tile.TileManager;
import ui.UI;

public class GamePanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	// SCREEN SETTINGS
	public int originalTileSize = 16; // 16x16 Tile
	public int scale = 3;
	public int tileSize = originalTileSize * scale; // 48x48 Tile
	public int maxScreenCol = 16;
	public int maxScreenRow = 12;
	public int screenWidth = tileSize * maxScreenCol; // 768px
	public int screenHeight = tileSize * maxScreenRow; // 576px
	
	// WORLD SETTINGS
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	
	// FPS
	final int FPS = 60;
	final double drawInterval = 1000000000 / FPS;
	double delta = 0;
	
	// MANAGERS
	public TileManager tileManager = new TileManager(this);
	public KeyHandler keyHandler = new KeyHandler();
	public SoundManager musicManager = new SoundManager();
	public SoundManager soundEffectManager = new SoundManager();
	public CollisionManager collisionChecker = new CollisionManager(this);
	public ItemManager itemManager = new ItemManager(this);
	public UI ui = new UI(this);
	
	// GAME THREAD
	public Thread gameThread;

	// PLAYER AND ITEMS
<<<<<<< HEAD
	public Player player = new Player(this, keyHandler);
=======
	public Player player;
	public List<Item> items = new ArrayList<Item>();
>>>>>>> branch 'master' of https://github.com/willmead/JavaZelda.git
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		player = new Player(this, keyHandler);
		itemManager.loadDemoItems();
		tileManager.loadDemoTileset();
		tileManager.loadDemoMap("/maps/map01.txt");
		soundEffectManager.loadDemoSounds();
		musicManager.loadDemoSounds();
		musicManager.play("soundtrack");
		musicManager.loop();
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
		ui.update();
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
