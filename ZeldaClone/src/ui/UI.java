package ui;

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
	
	public void update() {
		messages.addAll(toAdd);
		toAdd.clear();
		messages.removeAll(toRemove);
		toRemove.clear();
	}

	public void draw(Graphics2D g2) {
		
		fontMetrics = g2.getFontMetrics();
		
		for (Message message : messages) {
			g2.setFont(arial_40);
			g2.setColor(message.color);
			
			Rectangle2D stringBounds = fontMetrics.getStringBounds(message.text, g2);
			double x = message.x - (stringBounds.getWidth() / 2d);
			double y = message.y - (stringBounds.getHeight() / 2d);
			g2.drawString(message.text, (int)x, (int)y);
			
			long currentTime = System.nanoTime();
			if (((currentTime - message.startTime) / 1000000000) > message.seconds) {
				toRemove.add(message);
			}
		}
		

	}
}
