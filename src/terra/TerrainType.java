package terra;

import java.awt.Color;

public enum TerrainType 
{
	DEEP_WATER(new Color(54, 107, 193)),
	MEDIUM_WATER(new Color(64, 132, 226)),
	SHALLOW_WATER(new Color(107, 190, 242)),
	
	SAND(new Color(255, 253, 165)),
	
	FOREST(new Color(90, 123, 49)),
	WOODS(new Color(106, 146, 57)),
	PLAINS(new Color(129, 183, 71)),
	
	TALL_MOUNTAIN(new Color(69, 69, 69)),
	MEDIUM_MOUNTAIN(new Color(87, 87, 87)),

	SNOW(new Color(250, 250, 255));
	
	private Color terrainColor;
	
	private TerrainType(Color terrainColor)
	{
		this.terrainColor = terrainColor;
	}
	
	public Color getTerrainColor()
	{
		return terrainColor;
	}
}
