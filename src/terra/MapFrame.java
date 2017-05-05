package terra;

import javax.swing.JFrame;

public class MapFrame extends JFrame
{
	private final String TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	private final MapPanel MAP_PANEL;
	private final boolean IS_VISIBLE;
	private final boolean IS_RESIZABLE;
	
	public MapFrame(String title, int width, int height, MapPanel mapPanel, boolean isVisible, boolean isResizable)
	{
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.MAP_PANEL = mapPanel;
		this.IS_VISIBLE = isVisible;
		this.IS_RESIZABLE = isResizable;
		
		// TODO setIconImage(image);

		setTitle(TITLE);
		add(MAP_PANEL);
        pack();
        setSize(WIDTH, HEIGHT);
        setVisible(IS_VISIBLE);
        setResizable(IS_RESIZABLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
