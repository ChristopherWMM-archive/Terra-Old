package terra;

import java.awt.image.BufferedImage;

public class Terra 
{
	private static final int WIDTH = 512;
	private static final int HEIGHT = 512;
	
	private static final String APPLICATION_TITLE = "Terra";
	
    public static void main(String[] args) 
    {
    	BufferedImage baseNoise = new PerlinNoise2D(WIDTH, HEIGHT, 5, 0.5, 3, 0, new NoiseFallOffMap(WIDTH, HEIGHT, 20)).getNoiseImage();
    	
    	BufferedImage heightMap = new HeightMapGenerator(WIDTH, HEIGHT, new PerlinNoise2D(WIDTH, HEIGHT, 5, 0.5, 3, 0, new NoiseFallOffMap(WIDTH, HEIGHT, 20))).getImage();
        
        MapPanel noisePanel = new MapPanel(baseNoise);
        MapPanel heightMapPanel = new MapPanel(heightMap);

        MapFrame noiseFrame = new MapFrame(APPLICATION_TITLE, WIDTH, HEIGHT, noisePanel, true, false);
        MapFrame heightMapFrame = new MapFrame(APPLICATION_TITLE, WIDTH, HEIGHT, heightMapPanel, true, false);
    }
}
