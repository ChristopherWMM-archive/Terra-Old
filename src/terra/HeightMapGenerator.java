package terra;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HeightMapGenerator 
{
	private int width;
	private int height;
	
	private PerlinNoise2D noiseGenerator;
	
	Color deepWater = new Color(54, 107, 193);
	Color mediumWater = new Color(64, 132, 226);
	Color shallowWater = new Color(107, 190, 242);
	
	Color sand = new Color(255, 253, 165);
	Color lightGrass = new Color(129, 183, 71);
	Color mediumGrass = new Color(106, 146, 57);
	Color darkGrass = new Color(90, 123, 49);
	
	Color mediumStone = new Color(87, 87, 87);
	Color darkStone = new Color(69, 69, 69);
	
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
            	
				if (noise[x][y] > 0.98) 
				{
					mapImage.setRGB(x, y, Color.white.getRGB());
				}
				else if (noise[x][y] > 0.95) 
				{
					mapImage.setRGB(x, y, darkStone.getRGB());
				}
				else if (noise[x][y] > 0.9) 
				{
					mapImage.setRGB(x, y, mediumStone.getRGB());
				}
				else if (noise[x][y] > 0.85)
				{
					mapImage.setRGB(x, y, darkGrass.getRGB());
				}
				else if (noise[x][y] > 0.8) 
				{
					mapImage.setRGB(x, y, mediumGrass.getRGB());
				}
				else if (noise[x][y] > 0.75) 
				{
					mapImage.setRGB(x, y, lightGrass.getRGB());
				}
				else if (noise[x][y] > 0.7) 
				{
					mapImage.setRGB(x, y, sand.getRGB());
				}
				else if (noise[x][y] > 0.65) 
				{
					mapImage.setRGB(x, y, shallowWater.getRGB());
				}
				else if (noise[x][y] > 0.6) 
				{
					mapImage.setRGB(x, y, mediumWater.getRGB());
				}
				else if (noise[x][y] > 0.55) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.5) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.45) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.4) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.35) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.3) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.25) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.2) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.15) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.1) 
				{
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
				else if (noise[x][y] > 0.05) 
				{

				}

				if (noise[x][y] < noiseGenerator.mean) {
					mapImage.setRGB(x, y, deepWater.getRGB());
				}
			}
		}

		return mapImage;
	}
}