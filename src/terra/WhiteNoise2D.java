package terra;

import java.awt.image.BufferedImage;
import java.util.Random;

public class WhiteNoise2D extends Noise2D
{
	private Random random = new Random();
	
	public WhiteNoise2D()
	{
		super();
	}
	
	public WhiteNoise2D(int width, int height)
	{
		super(width, height);
	}
	
	public WhiteNoise2D(int width, int height, NoiseFallOffMap noiseFallOffMap)
	{
		super(width, height, noiseFallOffMap);
	}
	
	public double getNoiseValue(int x, int y)
	{
		return generateNoiseValue();
	}
	
	public double[][] getNoiseArray()
	{
		return generateNoiseArray();
	}
	
	public BufferedImage getNoiseImage()
	{
		return generateNoiseImage();
	}

	private double generateNoiseValue() 
	{
		return random.nextDouble();
	}
	
	@Override
	protected double[][] generateNoiseArray() 
	{
		double[][] noiseValues = new double[width][height];
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				noiseValues[x][y] = generateNoiseValue();

				noiseValues[x][y] = noiseFallOffMap.getNoiseValue(x, y, noiseValues[x][y]);
			}
		}
		
		return noiseValues;
	}
	
	@Override
	protected BufferedImage generateNoiseImage() 
	{
		BufferedImage noiseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		double[][] noise = generateNoiseArray();
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double noiseValue = noise[x][y];
				
				int blue = (int)(noiseValue * 0xFF);
    			int green = blue * 0x100;
    			int red = blue * 0x10000;
    			int finalColor = red + green + blue;
    			
    			noiseImage.setRGB(x, y, finalColor);
			}
		}
		
		return noiseImage;
	}
}
