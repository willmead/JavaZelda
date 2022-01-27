package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.GamePanel;

public class UI {
	
	GamePanel gp;
	Font arial_40, arial_80B;
		
	public boolean gameFinished = false;
	
	List<Message> toAdd = new ArrayList<Message>();
	List<Message> messages = new ArrayList<Message>();
	
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
	}

	public void draw(Graphics2D g2) {
		
		update();
		
		List<Message> toRemove = new ArrayList<Message>();
		
		for (Message message : messages) {
			g2.setFont(arial_40);
			g2.setColor(message.color);
			
			int textLength = (int)g2.getFontMetrics().getStringBounds(message.text, g2).getWidth();
			int textHeight = (int)g2.getFontMetrics().getStringBounds(message.text, g2).getHeight();

			int x = message.x - textLength / 2;
			int y = message.y - textHeight / 2;
			g2.drawString(message.text, x, y);
			
			long currentTime = System.nanoTime();
			System.out.println((currentTime - message.startTime) / 1000000000);
			if (((currentTime - message.startTime) / 1000000000) > message.seconds) {
				toRemove.add(message);
			}
		}
		
		messages.removeAll(toRemove);

	}
}
