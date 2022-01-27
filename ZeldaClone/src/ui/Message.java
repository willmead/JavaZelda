package ui;

import java.awt.Color;
import java.awt.Font;

public class Message {
	
	String text;
	Color color;
	Font font;
	int x;
	int y;
	int seconds;
	
	long startTime;
	
	public Message(String text, Color color, Font font, int x, int y, int seconds) {
		super();
		this.text = text;
		this.color = color;
		this.font = font;
		this.x = x;
		this.y = y;
		this.seconds = seconds;
		
		startTime = System.nanoTime();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

}
