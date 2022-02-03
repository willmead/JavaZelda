package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class UI {
	
	GamePanel gp;
	public Font arial_40, arial_80B;
		
	public boolean gameFinished = false;
	
	List<Message> toAdd = new ArrayList<Message>();
	List<Message> messages = new ArrayList<Message>();
	List<Message> toRemove = new ArrayList<Message>();
	
	FontMetrics fontMetrics;

	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
	}
	
	public void displayMessage(String text, Color color, Font font, int x, int y, int seconds) {
		Message message = new Message(text, color, font, x, y, seconds);
		toAdd.add(message);
	}
	
	public void drawSubWindow(Graphics2D g2) {
		int x = gp.tileSize / 2;
		int y = gp.screenHeight - gp.tileSize * 9 / 2;
		int width = gp.screenWidth - gp.tileSize;
		int height = gp.screenHeight / 3;
				
		g2.setColor(Color.black);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}
		
	public void update() {
		messages.addAll(toAdd);
		toAdd.clear();
		messages.removeAll(toRemove);
		toRemove.clear();
	}

	public void draw(Graphics2D g2) {
		
		int x = (gp.tileSize / 2) + gp.tileSize;
		int y = (gp.screenHeight - gp.tileSize * 9 / 2) + gp.tileSize;
		
		fontMetrics = g2.getFontMetrics();
		
		for (Message message : messages) {
			drawSubWindow(g2);
			g2.setFont(message.font);
			g2.setColor(message.color);
			
			for (String line : message.text.split("\n")) {
				g2.drawString(line, x, y);
				y += 40;
			}
			
			long currentTime = System.nanoTime();
			if (((currentTime - message.startTime) / 1000000000) > message.seconds) {
				toRemove.add(message);
			}
		}
		

	}
}
