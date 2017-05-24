package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
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

import exception.ArgumentOutOfBoundsException;
import generator.HeightMapGenerator;
import noise.NoiseFallOffMap;
import noise.PerlinNoise2D;

public class TerraFrame extends JFrame
{
	private Random random = new Random();
	
	private final String APPLICATION_TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	
	private static ArrayList<MapFrame> terrains = new ArrayList<MapFrame>();;
	
	public TerraFrame(String title, int width, int height)
	{
		this.APPLICATION_TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;

		setTitle(APPLICATION_TITLE);
		add(new TerraPanel());
		pack();
        setSize(WIDTH, HEIGHT);
        setIconImage(new HeightMapGenerator(32, 32, new PerlinNoise2D(32, 32, 0.15, 6, 0.5, 3, random.nextLong())).getImage());
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void saveImages()
	{
		try 
		{
			int x = 0;
			
			if (terrains.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Error: No Terrains to export!", APPLICATION_TITLE, 0);
				return;
			}
			
			for (MapFrame terrain : terrains)
			{
				BufferedImage image = terrain.getPanel().getImage();
				String seed = terrain.getTitle().replaceAll("Terra - Seed: ", "");
				
				File outputImage = new File(image.hashCode() + "-" + seed + ".png");
				ImageIO.write(image, "png", outputImage);
				x++;
			}
			
			JOptionPane.showMessageDialog(null, (x > 1) ? (x + " images were saved!") : (x + " image was saved!"), APPLICATION_TITLE, 1);
		} 
		catch (IOException exception) 
		{
			JOptionPane.showMessageDialog(null, "Error: Images could not be saved!", APPLICATION_TITLE, 0);
		}
	}

	public void generateTerrain(int width, int height, double roughness, int octaves, double persistence, double lacunarity, long seed, int fallOffMapIntensity, boolean useFallOffMap, boolean showRawNoise) 
	{
		BufferedImage baseNoise;
		BufferedImage heightMap;

		if (useFallOffMap)
		{
			baseNoise = new PerlinNoise2D(width, height, roughness, octaves, persistence, lacunarity, seed, new NoiseFallOffMap(width, height, fallOffMapIntensity)).getNoiseImage();
			heightMap = new HeightMapGenerator(width, height, new PerlinNoise2D(width, height, roughness, octaves, persistence, lacunarity, seed, new NoiseFallOffMap(width, height, fallOffMapIntensity))).getImage();
		}
		else
		{
			baseNoise = new PerlinNoise2D(width, height, roughness, octaves, persistence, lacunarity, seed).getNoiseImage();
			heightMap = new HeightMapGenerator(width, height, new PerlinNoise2D(width, height, roughness, octaves, persistence, lacunarity, seed)).getImage();
		}

		if (showRawNoise)
		{
	        new MapFrame(APPLICATION_TITLE + " - Seed: " + seed, width, height, new MapPanel(baseNoise));
		}

		new MapFrame(APPLICATION_TITLE + " - Seed: " + seed, width, height, new MapPanel(heightMap));
	}
	
	public static ArrayList<MapFrame> getTerrains()
	{
		return terrains;
	}
	
	private class TerraPanel extends JPanel
	{
		JCheckBox showNoiseCheckBox;
		JLabel showNoiseLabel;
		
		JLabel widthLabel;
		JTextField widthField;
		
		JLabel heightLabel;
		JTextField heightField;
		
		JLabel roughnessLabel;
		JTextField roughnessField;
		
		JLabel octavesLabel;
		JTextField octavesField;
		
		JLabel persistenceLabel;
		JTextField persistenceField;
		
		JLabel lacunarityLabel;
		JTextField lacunarityField;

		JLabel randomSeedCheckBoxLabel;
		JCheckBox randomSeedCheckBox;
		
		JLabel seedLabel;
		JTextField seedField;

		JCheckBox fallOffMapCheckBox;
		JLabel fallOffMapLabel;
		
		JLabel fallOffMapIntensityLabel;
		JTextField fallOffMapIntensityField;
		
		JLabel generateLabel;
		JButton generateButton;
		
		JLabel exportLabel;
		JButton exportButton;
		
		public TerraPanel()
		{
			setLayout(new GridLayout(26, 1));
			setBorder(BorderFactory.createCompoundBorder(new TitledBorder("Terrain Options"), new EmptyBorder(0, 5, 0, 5)));
			
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
			
			roughnessLabel = new JLabel("Noise Roughness:");
			roughnessField = new JTextField(10);
			roughnessField.setText("0.2");
			roughnessField.setToolTipText("The overall pixel roughness of the generated terrain. (Double)");
			
			octavesLabel = new JLabel("Noise Octaves:");
			octavesField = new JTextField(10);
			octavesField.setText("6");
			octavesField.setToolTipText("The number of compound noise iterations that will be compiled together. (Integer)");
			
			persistenceLabel = new JLabel("Noise Persistence:");
			persistenceField = new JTextField(10);
			persistenceField.setText("0.5");
			persistenceField.setToolTipText("The impact each noise iteration has on the previous iterations. (Double)");
			
			lacunarityLabel = new JLabel("Noise Lacunarity:");
			lacunarityField = new JTextField(10);
			lacunarityField.setText("2.8");
			lacunarityField.setToolTipText("The distance between each node in the noise. (Double)");
			
			randomSeedCheckBoxLabel = new JLabel();
			randomSeedCheckBox = new JCheckBox("Random Seed", false);
			randomSeedCheckBox.setToolTipText("Generates a random seed.");
			
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
			generateButton.setToolTipText("Creates a new terrain based on the given inputs.");
			
			generateButton.addActionListener(new ActionListener() 
			{ 
				public void actionPerformed(ActionEvent event) 
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
						
						double roughness = Double.parseDouble(roughnessField.getText());
						
						if (roughness < 0)
						{
							throw new ArgumentOutOfBoundsException("Error: Noise roughness must be greater than zero!");
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
						
						if (randomSeedCheckBox.isSelected())
						{
							seed = random.nextLong();
						}
						
						generateTerrain(width, height, roughness, octaves, persistence, lacunarity, seed, fallOffMapIntensity, useFallOffMap, showRawNoise);
						exportButton.setEnabled(true);
					}
					catch (NumberFormatException exception)
					{
						JOptionPane.showMessageDialog(null, "Error: Invalid Input Type!", APPLICATION_TITLE, 0);
					}
					catch (ArgumentOutOfBoundsException exception)
					{
						JOptionPane.showMessageDialog(null, exception.getMessage(), APPLICATION_TITLE, 0);
					}
				} 
			});
			
			exportLabel =  new JLabel();
			exportButton = new JButton("Export");
			exportButton.setToolTipText("Saves all currently displaying terrain as images.");
			exportButton.setEnabled(false);
			
			exportButton.addActionListener(new ActionListener() 
			{ 
				public void actionPerformed(ActionEvent event) 
				{
					saveImages();
				}
			});
			
			add(showNoiseCheckBox);
			add(showNoiseLabel);
			
			add(widthLabel);
			add(widthField);
			
			add(heightLabel);
			add(heightField);
			
			add(roughnessLabel);
			add(roughnessField);
			
			add(octavesLabel);
			add(octavesField);
			
			add(persistenceLabel);
			add(persistenceField);
			
			add(lacunarityLabel);
			add(lacunarityField);
			
			add(randomSeedCheckBoxLabel);
			add(randomSeedCheckBox);
			
			add(seedLabel);
			add(seedField);
			
			add(fallOffMapLabel);
			add(fallOffMapCheckBox);
			
			add(fallOffMapIntensityLabel);
			add(fallOffMapIntensityField);
			
			add(generateLabel);
			add(generateButton);
			
			add(exportLabel);
			add(exportButton);
			
			pack();
		}
	}
}
