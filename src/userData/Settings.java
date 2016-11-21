package userData;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import files.FileSystem;
import mainGUI.SpellBookLauncher;

public class Settings {
	
	/**
	 * The mainwindow's behavior of centering (see getter)
	 */
	private static int centerFrames;
	/**
	 * Automatic window scaling based on screen resolution
	 */
	private static double scaleFactor;
	/**
	 * User determined window scaling
	 */
	private static double scaleAdjustment;
	
	private static ArrayList<ActionListener> resizeObservers = 
			new ArrayList<ActionListener>();
	
	/**
	 * Loads the preferences from file
	 */
	public static void loadPreferences()
	{
		try {
			String[] preferences = FileSystem.loadPreferences();
			centerFrames = Integer.parseInt(preferences[0]);
			scaleFactor = SpellBookLauncher.getScale();
			scaleAdjustment = Double.parseDouble(preferences[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the user's preferences to file
	 */
	public static void savePreferences()
	{
		String[] preferences = {centerFrames + "", 
				scaleAdjustment + ""};
		FileSystem.saveUserPref(preferences);
	}
	
	/**
	 * The mainwindow's behavior of centering 
	 * child frames upon summoning
	 * 0 - No centering
	 * 1 - Only center when setting visible
	 * 2 - Center whenever frame is requested
	 * @return centerFramesOnCall setting
	 */
	public static int getCenterFrames()
	{
		return centerFrames;
	}
	
	/**
	 * The mainwindow's behavior of centering 
	 * child frames upon summoning
	 * 0 - No centering
	 * 1 - Only center when setting visible
	 * 2 - Center whenever frame is requested
	 * @param setting
	 */
	public static void setCenterFrames(int setting)
	{
		centerFrames = setting;
	}
	
	/**
	 * Returns the amount the window should scale based on 
	 * the screen resolution and user preference
	 * @return double resize value
	 */
	public static double getResizeFactor(){
		return scaleFactor * scaleAdjustment;
	}
	
	/**
	 * Returns the size multiplier the user set for their windows
	 * @return
	 */
	public static double getScaleAdjustment(){
		return scaleAdjustment;
	}
	
	/**
	 * Sets the scale adjustment value
	 * @param adjustment
	 */
	public static void setScaleAdjustment(double adjustment)
	{
		if (adjustment != scaleAdjustment){
			scaleAdjustment = adjustment;
			notifyResizeObservers();
		}
	}
	
	/**
	 * Adds a actionListener to detect when the window 
	 * scale value is changed
	 * @param aL ActionListener
	 */
	public static void addResizeListener(ActionListener aL)
	{
		resizeObservers.add(aL);
	}
	
	/**
	 * Sends a message out to all action listeners
	 */
	private static void notifyResizeObservers()
	{
		for (ActionListener aL : resizeObservers)
		{
			aL.actionPerformed(null);
		}
	}
}
