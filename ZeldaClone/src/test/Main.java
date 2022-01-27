package test;

import javax.swing.JFrame;

import main.GamePanel;

public class Main {
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("ZeldaClone");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		window.setVisible(true);
	}

}
