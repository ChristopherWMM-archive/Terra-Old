package gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.JFrame;

import generator.HeightMapGenerator;
import noise.PerlinNoise2D;

public class MapFrame extends JFrame implements WindowListener
{
	private Random random = new Random();
	
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
		setIconImage(new HeightMapGenerator(32, 32, new PerlinNoise2D(32, 32, 0.15, 6, 0.5, 3, random.nextLong())).getImage());
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		addWindowListener(this);
	}
	
	public MapPanel getPanel()
	{
		return MAP_PANEL;
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		TerraFrame.getTerrains().add(this);
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		TerraFrame.getTerrains().remove(this);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
