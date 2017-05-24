package generator;

import java.awt.image.BufferedImage;

import noise.PerlinNoise2D;
import terra.TerrainType;

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
		double[][] noise = noiseGenerator.getNoiseArray();
		
		for (int x = 0; x < mapImage.getWidth(); x++) 
		{
			for (int y = 0; y < mapImage.getHeight(); y++) 
			{	
				if (noise[x][y] > 0.98) 
				{
					mapImage.setRGB(x, y, TerrainType.SNOW.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.95) 
				{
					mapImage.setRGB(x, y, TerrainType.TALL_MOUNTAIN.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.9) 
				{
					mapImage.setRGB(x, y, TerrainType.MEDIUM_MOUNTAIN.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.85)
				{
					mapImage.setRGB(x, y, TerrainType.FOREST.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.8) 
				{
					mapImage.setRGB(x, y, TerrainType.WOODS.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.73) 
				{
					mapImage.setRGB(x, y, TerrainType.PLAINS.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.7) 
				{
					mapImage.setRGB(x, y, TerrainType.SAND.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.65) 
				{
					mapImage.setRGB(x, y, TerrainType.SHALLOW_WATER.getTerrainColor().getRGB());
				}
				else if (noise[x][y] > 0.6) 
				{
					mapImage.setRGB(x, y, TerrainType.MEDIUM_WATER.getTerrainColor().getRGB());
				}
				else
				{
					mapImage.setRGB(x, y, TerrainType.DEEP_WATER.getTerrainColor().getRGB());
				}
				
				if (noise[x][y] < noiseGenerator.getMeanNoiseValue()) 
				{
					mapImage.setRGB(x, y, TerrainType.DEEP_WATER.getTerrainColor().getRGB());
				}
			}
		}

		return mapImage;
	}
}