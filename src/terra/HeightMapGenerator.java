package terra;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HeightMapGenerator 
{
	private int width;
	private int height;
	
	private PerlinNoise2D noiseGenerator;
	
	public HeightMapGenerator()
	{
		this.width = 0;
		this.height = 0;
		this.noiseGenerator = new PerlinNoise2D();
	}
	
	public HeightMapGenerator(int width, int height, PerlinNoise2D noiseGenerator)
	{
		this.width = width;
		this.height = height;
		this.noiseGenerator = noiseGenerator;
	}
	
	public BufferedImage getImage()
	{
		BufferedImage mapImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		double[][] noise = noiseGenerator.generateNoiseArray();
		
		for (int x = 0; x < mapImage.getWidth(); x++) 
        {
            for (int y = 0; y < mapImage.getHeight(); y++) 
            {	
            	// TODO: Establish an actual algorithm here.
            	
            	if (noise[x][y] > 1) {
            		mapImage.setRGB(x, y, Color.black.getRGB());
            	} else if (noise[x][y] > 0.95) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 2);
            	} else if (noise[x][y] > 0.9) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 3);
            	} else if (noise[x][y] > 0.85) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 4);
            	} else if (noise[x][y] > 0.8) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 5);
            	} else if (noise[x][y] > 0.75) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 6);
            	} else if (noise[x][y] > 0.7) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 7);
            	} else if (noise[x][y] > 0.65) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 8);
            	} else if (noise[x][y] > 0.6) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 9);
            	} else if (noise[x][y] > 0.55) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 10);
            	} else if (noise[x][y] > 0.5) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 11);
            	} else if (noise[x][y] > 0.45) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 12);
            	} else if (noise[x][y] > 0.4) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 13);
            	} else if (noise[x][y] > 0.35) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 14);
            	} else if (noise[x][y] > 0.3) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 15);
            	} else if (noise[x][y] > 0.25) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 16);
            	} else if (noise[x][y] > 0.2) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 17);
            	} else if (noise[x][y] > 0.15) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 18);
            	} else if (noise[x][y] > 0.1) {
            		mapImage.setRGB(x, y, Color.black.getRGB() / 19);
            	} else if (noise[x][y] > 0.05) {
            		
            	}
            }
        }
		
		return mapImage;
	}
}