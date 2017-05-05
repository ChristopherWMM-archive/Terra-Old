package terra;

import java.awt.image.BufferedImage;

public class NoiseFallOffMap
{
	private int width;
	private int height;
	private double intensity;
	
	public NoiseFallOffMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.intensity = 1;
	}
	
	public NoiseFallOffMap(int width, int height, double intensity)
	{
		this.width = width;
		this.height = height;
		this.intensity = intensity;
	}
	
	public double getNoiseValue(int x, int y, double value)
	{
		return generateNoiseValue(x, y, intensity, value);
	}
	
	public double[][] getNoiseArray()
	{
		return generateNoiseArray();
	}
	
	public BufferedImage getNoiseImage()
	{
		return generateNoiseImage();
	}
	
	private double generateNoiseValue(int x, int y, double intensity, double value)
    {
        double minVal = (((height + width) / 2) / 100);
        double maxVal = (((height + width) / 2) / 100 * intensity);
        
        if (intensity > 1)
        {
	        if (calculateDistanceToEdge(x, y) <= minVal)
	        {
	            return 0;
	        }
	        else if (calculateDistanceToEdge(x, y) >= maxVal)
	        {
	            return value;
	        }
	        else 
	        {
	        	double possibleMax = maxVal - minVal;
	        	double currentValue = calculateDistanceToEdge(x, y) - minVal;
	            double fadeFactor = currentValue / possibleMax;

	            return (value * fadeFactor);
	        }
        }
        else
        {
        	return value;
        }
    }

	private double[][] generateNoiseArray() 
	{
		double[][] noiseValues = new double[width][height];
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				noiseValues[x][y] = generateNoiseValue(x, y, intensity, 1);
			}
		}
		
		return noiseValues;
	}

	private BufferedImage generateNoiseImage() 
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
    
    private int calculateDistanceToEdge(int x, int y)
    {
        int[] distances = new int[] {x, y, (width), (height), (width - x - 7), (height - y - 28)};
        int min = distances[0];
        for(int value : distances)
        {
            if (value < min)
            {
                min = value;
            }
        }
        return min;
    }
    
    public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public double getIntensity()
	{
		return intensity;
	}
	
	public void setIntensity(double intensity)
	{
		this.intensity = intensity;
	}
}
