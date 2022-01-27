package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UtilityTool {

	public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		return scaledImage;
	}
	
	public static BufferedImage loadImage(String imageLocation, String imageName) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(UtilityTool.class.getResourceAsStream("/" + imageLocation + "/" + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scaleImage(image, 48, 48);
	}

}
