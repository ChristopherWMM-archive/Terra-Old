package terra;

import gui.TerraFrame;

/**
 * -------------------------------------------------------------------------------------------------------------------------------------
 * Course: CS 112 Section 1287
 * Days & Time: 5/22/17 1:55pm
 * Chapter Number: Final Project
 * Programmer(s): Christopher Martin
 * Date Created or Last Modified: 5/22/17
 * Program Title: Terra
 * Program Description: 2D Terrain Generator based on Perlin Noise.
 * The purpose of this program is to: Implement Perlin Noise and GUI's to create a user friendly 2D Terrain Generator.
 * -------------------------------------------------------------------------------------------------------------------------------------
 * Algorithm:
 * 	Terra
 * 		main
 * 			Create a new TerraFrame.
 * 
 * 	TerraFrame
 * 		Initialize variables to hold the application title, width, and height.
 * 		Create an ArrayList to keep track of all active windows.
 * 
 * 		Create a default constructor.
 * 			Set the various settings for the JFrame (Title, Icon, Visibility, Resizeability, Location, and Close Operation)
 * 			Add a TerraPanel.
 * 
 * 		saveImages
 * 			Try to export each image on the frames in the ArrayList.
 * 			Catch any IOExceptions and display a JOptionPane to inform the user of the error.
 * 
 * 		generateTerrain
 * 			Initialize a BufferedImage for each possible map.
 * 			If useFallOffMap is true than create new images with a FallOffMap.
 * 			Or, create the images without a FallOffMap
 * 			If showRawNoise is true than create a new MapFrame with the noise image
 * 			Create a new MapFrame with the height map image.
 * 
 * 		getTerrains
 * 			Return the ArrayList of active MapFrames.
 * 
 * 		$TerraPanel
 * 			Initialize variables to hold the JLabels and JTextFields for each variable along with enable JCheckBoxes for raw noise, fall off map, and randomized seeds.
 * 			
 * 			Create a default constructor.
 * 				Set up a new GridLayout and CompoundBorder.
 * 				Create the JLabels and JTextFields for each variable along with enable JCheckBoxes for raw noise, fall off map, and randomized seeds.
 * 				Add ToolTips to each component.
 * 				Add an actionListener to the Generate button to call the generateTerrain method.
 * 				Add an actionListener to the Export button to call the saveImages method.
 * 				Add the various components to the MapPanel.
 * 
 * 	MapFrame
 * 		Initialize variables to hold the application title, width, height and panel.
 * 		
 * 		Create a default constructor.
 * 			Set the various settings for the JFrame (Title, Icon, Visibility, Resizeability, Location, and Close Operation)
 * 			Add a MapPanel.
 * 
 * 		getPanel
 * 			Return the MapPanel.
 * 
 * 		windowOpened
 * 			Add this MapFrame to the ArrayList of MapFrames in TerraFrame.
 * 
 * 		windowClosing
 * 			Remove this MapFrame from the ArrayList of MapFrames in TerraFrame.
 *
 *	MapPanel
 *		Initialize a variable to hold the MapPanel's image.
 *
 *		Create a default constructor.
 *			Set the value of the passed image.
 *
 *		paintComponent
 *			Draw the image onto the MapPanel.
 *
 *		getImage
 *			Return the image on this MapPanel.
 *
 *	HeightMapGenerator
 *		Initialize variables to hold the width, height, noise, and colors of the new terrain.
 *		
 *		Create a default constructor.
 *			Set the values of the passed width, height, and noise to 0.
 *
 *		Create the main argument constructor.
 *			Set the values of the passed width, height, and noise.
 *
 *		getImage
 *			Create a new BufferedImage.
 *			Loop through the noise data to set each pixel of the new image.
 *			Return the image.
 *
 *	Noise2D
 *		Initialize variables to hold the width, height, and FallOffMap.
 *
 *		Create a default constructor.
 *			Set the values of the passed width, height, and FallOffMap to 0.
 *
 *		Create the main argument constructor.
 *			Set the values of the passed width, height, and FallOffMap.
 *
 *		Create an abstract generateNoiseArray method.
 *
 *		Create an abstract generateNoiseImage method.
 *
 *		getWidth
 *			Return the width.
 *
 *		setWidth
 *			Set the new width.
 *
 *		getHeight
 *			Return the height.
 *
 *		setHeight
 *			Set the new height.
 *
 *		getFallOffMap
 *			Return the FallOffMap.
 *
 *		setFallOffMap
 *			Set the new FallOffMap.
 *
 *	PerlinNoise2D
 *		Initialize variables to hold the frequency, octaves, persistence, lacunarity, seed, and mean.
 *		Initialize the permutation table.
 *
 *		Create a default constructor.
 *			Set the variables to 0.
 *
 *		Create the main argument constructors.
 *			Set the variables to the passed in values.
 *
 *		getNoiseValue
 *			Return a generated noise value.
 *
 *		getNoiseArray
 *			Return a generated noise array.
 *
 *		getNoiseImage
 *			Return a generated noise image.
 *
 *		generateNoiseValue
 *			Calculate the seeded X and Y value.
 *			Floor those values from 0 to 255.
 *			Calculate the vectors of the 4 grid corners.
 *			Calculate the adjusted pixel location of the current grid.
 *			Calculate the dot product of the four corners.
 *			Fade the X and Y value.
 *			Calculate the lerped X values and the lerped Y value.
 *			Return the lerped Y value.
 *
 *		generateOctaveNoiseValue
 *			Loop through all the octaves
 *			Generate compound noise values and add them all together.
 *
 *		generateNoiseArray
 *			Create a new double array.
 *			Loop to the width
 *				Loop to the height
 *					Set the current array value to an octave noise value.
 *			Add the FallOffMap
 *			Reinterpolate the noise array.
 *			Floor the array values from the min to the max.
 *
 *		generateNoiseImage
 *			Create a new BufferedImage
 *			Loop through a noise array.
 *				Set the current pixel to the shade of grey corresponding to the noise value.
 *
 *		lerp
 *			Return a lerped value between two passed variables.
 *
 *		inverseLerp
 *			Returns a values position in relation to the two passed in values.
 *
 *		interpolate
 *			Fades the passed in value.
 *
 *		calculateDotProduct
 *			Calculates the dot product of a grid corner and an x and y position.
 *
 *		getFrequency
 *			Return the frequency.
 *
 *		setFrequency
 *			Set the new frequency.
 *
 *		getOctaves
 *			Return the octaves.
 *
 *		setOctaves
 *			Set the new octaves.
 *
 *		getPersistence
 *			Return the persistence.
 *
 *		setPersistence
 *			Set the new persistence.
 *
 *		getLacunarity
 *			Return the lacunarity.
 *
 *		setLacunarity
 *			Set the new lacunarity.
 *
 *		getSeed
 *			Return the seed.
 *
 *		setSeed
 *			Set the new seed.
 *
 *	NoiseFallOffMap
 *		Initialize variables to hold the width, height, and intensity.
 *
 *		Create a default constructor.
 *			Set the variables to 0.
 *
 *		Create the main argument constructors.
 *			Set the variables to the passed in values.
 *		
 *		getNoiseValue
 *			Return a generated noise value.
 *
 *		getNoiseArray
 *			Return a generated noise array.
 *
 *		getNoiseImage
 *			Return a generated noise image.
 *
 *		generateNoiseValue
 *			Calculate the minimum and maximum value.
 *			Calculate the distance to the edge.
 *			Calculate how much the FallOffMap should fade the value.
 *			Return the value.
 *
 *		generateNoiseArray
 *			Create a new double array.
 *			Loop to the width
 *				Loop to the height
 *					Set the current array value to an octave noise value.
 *
 *		generateNoiseImage
 *			Create a new BufferedImage
 *			Loop through a noise array.
 *				Set the current pixel to the shade of grey corresponding to the noise value.
 *
 *		calculateDistanceToEdge
 *			Loop through all possible distances to the edge and return the lowest one.
 *
 *		getWidth
 *			Return the width.
 *
 *		setWidth
 *			Set the new width.
 *
 *		getHeight
 *			Return the height.
 *
 *		setHeight
 *			Set the new height.
 *
 *		getIntensity
 *			Return the intensity.
 *
 *		setIntensity
 *			Set the new intensity.
 *
 *	ArgumentOutOfBounds extends Exception
 * 		Create a default constructor.
 * 		Create a message constructor.
 * -------------------------------------------------------------------------------------------------------------------------------------
*/

public class Terra 
{
	private static final String APPLICATION_TITLE = "Terra";
	
	public static void main(String[] args) 
	{
		new TerraFrame(APPLICATION_TITLE, 200, 450);
	}
}