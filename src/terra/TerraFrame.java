package terra;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class TerraFrame extends JFrame
{
	private final String APPLICATION_TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	
	public TerraFrame(String title, int width, int height)
	{
		this.APPLICATION_TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;

		setTitle(APPLICATION_TITLE);
		add(new TerraPanel());
		pack();
        setSize(WIDTH, HEIGHT);
        setIconImage(new HeightMapGenerator(32, 32, new PerlinNoise2D(32, 32, 6, 0.5, 3, 4)).getImage());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void generateTerrain(int width, int height, int octaves, double persistence, double lacunarity, long seed, int fallOffMapIntensity, boolean useFallOffMap, boolean showRawNoise) 
	{
		BufferedImage baseNoise;
		BufferedImage heightMap;

		if (useFallOffMap)
		{
			baseNoise = new PerlinNoise2D(width, height, 5, persistence, lacunarity, seed, new NoiseFallOffMap(width, height, fallOffMapIntensity)).getNoiseImage();
			heightMap = new HeightMapGenerator(width, height, new PerlinNoise2D(width, height, octaves, persistence, lacunarity, seed, new NoiseFallOffMap(width, height, fallOffMapIntensity))).getImage();
		}
		else
		{
			baseNoise = new PerlinNoise2D(width, height, 5, persistence, lacunarity, seed).getNoiseImage();
			heightMap = new HeightMapGenerator(width, height, new PerlinNoise2D(width, height, octaves, persistence, lacunarity, seed)).getImage();
		}

		if (showRawNoise)
		{
	        new MapFrame(APPLICATION_TITLE, width, height, new MapPanel(baseNoise));
		}
	
		new MapFrame(APPLICATION_TITLE, width, height, new MapPanel(heightMap));
	}
	
	private class TerraPanel extends JPanel
	{
		JCheckBox showNoiseCheckBox;
		JLabel showNoiseLabel;
		
		JLabel widthLabel;
		JTextField widthField;
		
		JLabel heightLabel;
		JTextField heightField;
		
		JLabel octavesLabel;
		JTextField octavesField;
		
		JLabel persistenceLabel;
		JTextField persistenceField;
		
		JLabel lacunarityLabel;
		JTextField lacunarityField;
		
		JLabel seedLabel;
		JTextField seedField;

		JCheckBox fallOffMapCheckBox;
		JLabel fallOffMapLabel;
		
		JLabel fallOffMapIntensityLabel;
		JTextField fallOffMapIntensityField;
		
		JLabel generateLabel;
		JButton generateButton;
		
		public TerraPanel()
		{
			setLayout(new GridLayout(20, 1));
			setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Terrain Options"), new EmptyBorder(5, 5, 5, 5)));
			
			showNoiseCheckBox = new JCheckBox("Show Raw Noise", false);
			showNoiseCheckBox.setToolTipText("Toggles the displaying of raw noise data.");
			showNoiseLabel = new JLabel();
			
			widthLabel = new JLabel("Map Width:");
			widthField = new JTextField(10);
			widthField.setText("640");
			widthField.setToolTipText("The width of the generated terrain. (Integer)");
			
			heightLabel = new JLabel("Map Height:");
			heightField = new JTextField(10);
			heightField.setText("640");
			heightField.setToolTipText("The height of the generated terrain. (Integer)");
			
			octavesLabel = new JLabel("Noise Octaves:");
			octavesField = new JTextField(10);
			octavesField.setText("6");
			octavesField.setToolTipText("The number of compound noise iterations. (Integer)");
			
			persistenceLabel = new JLabel("Noise Persistence:");
			persistenceField = new JTextField(10);
			persistenceField.setText("0.5");
			persistenceField.setToolTipText("The impact each noise iteration has on roughness. (Double)");
			
			lacunarityLabel = new JLabel("Noise Lacunarity:");
			lacunarityField = new JTextField(10);
			lacunarityField.setText("2.8");
			lacunarityField.setToolTipText("The distance between each peak of noise roughness. (Double)");
			
			seedLabel = new JLabel("Noise Seed:");
			seedField = new JTextField(10);
			seedField.setText("0");
			seedField.setToolTipText("The seed by which the noise is generated. (Long)");
			
			fallOffMapCheckBox = new JCheckBox("Include Fall Off Map", true);
			fallOffMapCheckBox.setToolTipText("Toggles the use of a fall off map.");
			fallOffMapLabel = new JLabel();
			
			fallOffMapIntensityLabel = new JLabel("Fall Off Map Intensity:");
			fallOffMapIntensityField = new JTextField(10);
			fallOffMapIntensityField.setText("15");
			fallOffMapIntensityField.setToolTipText("The amount of impact the fall off map has on generated noise. (Integer)");
			
			generateLabel =  new JLabel();
			generateButton = new JButton("Generate");
			generateButton.addActionListener( new ActionListener() 
			{ 
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						int width = Integer.parseInt(widthField.getText());
						
						if (width < 64)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise width must be greater than zero!");
						}
						
						int height = Integer.parseInt(heightField.getText());
						
						if (height < 64)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise height must be greater than zero!");
						}
						
						int octaves = Integer.parseInt(octavesField.getText());
						
						if (octaves < 1)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise octaves must be greater than zero!");
						}
						
						double persistence = Double.parseDouble(persistenceField.getText());
						
						if (persistence < 0 || persistence > 1)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise persistence must be between zero and one!");
						}
						
						double lacunarity = Double.parseDouble(lacunarityField.getText());
						
						if (lacunarity < 0)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise lacunarity must be greater than zero!");
						}
						
						long seed = Long.parseLong(seedField.getText());
						
						int fallOffMapIntensity = Integer.parseInt(fallOffMapIntensityField.getText());
						
						if (fallOffMapIntensity < -1)
						{
							throw new ArgumentOutOfBoundsException("Error: Fall off map intensity must be positive!");
						}
						
						boolean showRawNoise = showNoiseCheckBox.isSelected();
						boolean useFallOffMap = fallOffMapCheckBox.isSelected();
						
						generateTerrain(width, height, octaves, persistence, lacunarity, seed, fallOffMapIntensity, useFallOffMap, showRawNoise);
					}
					catch (NumberFormatException exception)
					{
						JOptionPane.showMessageDialog(null, "Error: Invalid Input Type!", APPLICATION_TITLE, 2);
					}
					catch (ArgumentOutOfBoundsException exception)
					{
						JOptionPane.showMessageDialog(null, exception.getMessage(), APPLICATION_TITLE, 2);
					}
				} 
			});
			
			add(showNoiseCheckBox);
			add(showNoiseLabel);
			
			add(widthLabel);
			add(widthField);
			
			add(heightLabel);
			add(heightField);
			
			add(octavesLabel);
			add(octavesField);
			
			add(persistenceLabel);
			add(persistenceField);
			
			add(lacunarityLabel);
			add(lacunarityField);
			
			add(seedLabel);
			add(seedField);
			
			add(fallOffMapLabel);
			add(fallOffMapCheckBox);
			
			add(fallOffMapIntensityLabel);
			add(fallOffMapIntensityField);
			
			add(generateLabel);
			add(generateButton);
			
			pack();
		}
	}
}
