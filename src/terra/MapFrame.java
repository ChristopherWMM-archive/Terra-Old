package terra;

import javax.swing.JFrame;

public class MapFrame extends JFrame
{
	private final String TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	private final MapPanel MAP_PANEL;
	
	public MapFrame(String title, int width, int height, MapPanel mapPanel)
	{
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.MAP_PANEL = mapPanel;
		
		setTitle(TITLE);
		add(MAP_PANEL);
		pack();
		setSize(WIDTH, HEIGHT);
		setIconImage(new HeightMapGenerator(32, 32, new PerlinNoise2D(32, 32, 6, 0.5, 3, 4)).getImage());
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
