package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.border.LineBorder;

import files.FileSystem;
import helperClasses.gameVersion;

import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.GridLayout;

/**
 * The window for accessing and modifying 
 * the user's settings
 * @author Daniel Campman
 */
@SuppressWarnings("serial")
public class Settings extends JFrame {

	private JPanel contentPane;
	private JComboBox<Object> comboBoxCentering;
	private JComboBox<Object> comboBoxSize;
	
	private JPanel panelSpellBrowser;
	private JLabel lblSBrowser;
	private JCheckBox chckbxColor;
	
	private JComboBox<Object> comboBoxGameVersion;
	private JLabel lblGameVersion;
	
	private JLabel lblResizeValue;
	private JButton btnSave;
	private JButton btnSaveQuit;
	private JLabel lblWindowPositioningSetting;
	
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
	/**
	 * Whether spell browser panels should be colored to reflect spell level
	 */
	private static boolean SBColor;
	/**
	 * The version of D&D currently being played
	 */
	private static gameVersion version;
	
	private static ArrayList<ActionListener> resizeObservers = 
			new ArrayList<ActionListener>();

	/**
	 * Create the frame.
	 */
	public Settings() {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Image image = new ImageIcon("Resources/settings.PNG").getImage();
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblWindowPositioningSetting = new JLabel("Window Positioning Behavior");
		contentPane.add(lblWindowPositioningSetting);
		
		String[] centeringOptions = {"Do not move window on call",
				"Only center windows when first appear",
				"Center windows whenever button is clicked"};
		comboBoxCentering = new JComboBox<Object>(centeringOptions);
		contentPane.add(comboBoxCentering);
		
		lblResizeValue = new JLabel("Window Size");
		contentPane.add(lblResizeValue);
		
		String[] sizeOptions = {"Very Small", "Small", "Default", "Large", "Very Large"};
		comboBoxSize = new JComboBox<Object>(sizeOptions);
		contentPane.add(comboBoxSize);
		
		lblSBrowser = new JLabel("Spell Browser Settings");
		contentPane.add(lblSBrowser);
		
		panelSpellBrowser = new JPanel();
		panelSpellBrowser.setBorder(new LineBorder(new Color(150, 150, 150), 1, true));
		contentPane.add(panelSpellBrowser);
		panelSpellBrowser.setLayout(new GridLayout(0, 1, 0, 0));
		
		chckbxColor = new JCheckBox("Use color to distinguish spell level");
		panelSpellBrowser.add(chckbxColor);
		
		btnSave = new JButton("Save Preferences");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerFrames = comboBoxCentering.getSelectedIndex();
				setScaleAdjustment(0.75 + (1 * comboBoxSize.getSelectedIndex()*0.25));
				SBColor = chckbxColor.isSelected();
				setVersion(gameVersion.values()[comboBoxGameVersion.getSelectedIndex()]);
				savePreferences();
				refresh();
			}
		});
		contentPane.add(btnSave);
		
		btnSaveQuit = new JButton("Save & Close");
		btnSaveQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerFrames = comboBoxCentering.getSelectedIndex();
				setScaleAdjustment(0.75 + (1 * comboBoxSize.getSelectedIndex()*0.25));
				SBColor = chckbxColor.isSelected();
				setVersion(gameVersion.values()[comboBoxGameVersion.getSelectedIndex()]);
				savePreferences();
				setVisible(false);
			}
		});
		contentPane.add(btnSaveQuit);
		
		lblGameVersion = new JLabel("Game Version");
		lblGameVersion.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblGameVersion.setBounds((int)(10*scaleFactor),
				(int)(113*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(14*scaleFactor));
		contentPane.add(lblGameVersion);
		
		String[] gameVersions = {"Fifth Edition", "Pathfinder"};
		comboBoxGameVersion = new JComboBox<Object>(gameVersions);
		comboBoxGameVersion.setSelectedIndex(0);
		comboBoxGameVersion.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxGameVersion.setBounds((int)(10*scaleFactor), 
				(int)(133*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		contentPane.add(comboBoxGameVersion);
		
		refresh();
		Point USW = UserSpellWindow.getWindowLocation();
		setLocation(USW.x + 30, USW.y + 30);
	}
	
	/**
	 * Refreshes the window to make sure it reflects current information
	 */
	public void refresh(){
		//Reset Values
		comboBoxCentering.setSelectedIndex(centerFrames);
		comboBoxSize.setSelectedIndex((int)(scaleAdjustment*4) - 3);
		chckbxColor.setSelected(SBColor);
		
		//Get scale factor
		double scaleFactor = getResizeFactor();
		//scaleFactor = 2;
		
		//Resize all components
		setBounds(getX(), getY(),
				(int)(336*scaleFactor), 
				(int)(303*scaleFactor) - (int)(37 * (scaleFactor-1)));
		
		lblWindowPositioningSetting.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblWindowPositioningSetting.setBounds((int)(10*scaleFactor), 
				(int)(11*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(14*scaleFactor));
		
		comboBoxCentering.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxCentering.setBounds((int)(10*scaleFactor), 
				(int)(31*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		
		lblResizeValue.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblResizeValue.setBounds((int)(10*scaleFactor), 
				(int)(62*scaleFactor), 
				(int)(67*scaleFactor), 
				(int)(14*scaleFactor));
		
		comboBoxSize.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxSize.setBounds((int)(10*scaleFactor), 
				(int)(82*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		
		lblGameVersion.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblGameVersion.setBounds((int)(10*scaleFactor),
				(int)(113*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(14*scaleFactor));
		
		comboBoxGameVersion.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxGameVersion.setBounds((int)(10*scaleFactor), 
				(int)(133*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		
		lblSBrowser.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblSBrowser.setBounds((int)(10*scaleFactor), 
				(int)(164*scaleFactor), 
				(int)(250*scaleFactor), 
				(int)(14*scaleFactor));
		
		panelSpellBrowser.setBounds((int)(10*scaleFactor), 
				(int)(186*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(35*scaleFactor));
		
		chckbxColor.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSave.setBounds((int)(10*scaleFactor), 
				(int)(231*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(23*scaleFactor));
		
		btnSaveQuit.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSaveQuit.setBounds((int)(176*scaleFactor),
				(int)(231*scaleFactor),
				(int)(144*scaleFactor),
				(int)(23*scaleFactor));
	}
	
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
			SBColor = preferences[2].equals("true");
			if(preferences[3].equals("FIFTH_EDITION")) {
				version = gameVersion.FIFTH_EDITION;
			} else if (preferences[3].equals("PATHFINDER")){
				version = gameVersion.PATHFINDER;
			} else {
				version = gameVersion.FIFTH_EDITION;
				System.out.println("ERROR LOADING VERSION");
			}
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
		FileSystem.saveUserPref();
	}
	
	/**
	 * The mainwindow's behavior of centering 
	 * child frames upon summoning <br>
	 * 0 - No centering <br>
	 * 1 - Only center when setting visible <br>
	 * 2 - Center whenever frame is requested <br>
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
	 * Sets whether SpellBrowser panels should be colored 
	 * to reflect the spell's level
	 * @param colorPanels {@code boolean}
	 */
	public static void setSBColor(boolean colorPanels)
	{
		if (SBColor != colorPanels) {
			SBColor = colorPanels;
			if (version.equals(gameVersion.FIFTH_EDITION))
			{
				SpellBrowserFE.windowRefresh();
			} else if (version.equals(gameVersion.PATHFINDER))
			{
				SpellBrowserPF.windowRefresh();
			} else {
				System.out.println("ERROR");
			}
		}
	}
	
	/**
	 * Returns whether SpellBrowser panels should be colored 
	 * to reflect the spell's level
	 * @return {@code boolean} Returns <em>true</em> if the 
	 * coloration is turned on
	 */
	public static boolean getSBColor()
	{
		return SBColor;
	}
	
	/**
	 * Returns the version of D&D being used
	 * @return <strong>gameVersion</strong> an enum 
	 * containing the possible values 
	 */
	public static gameVersion getVersion()
	{
		return version;
	}
	
	/**
	 * Sets the version of the game and updates the 
	 * UserSpellWindow if the version changed
	 * @param versionName
	 */
	public static void setVersion(gameVersion newVersion)
	{
		if (!version.equals(newVersion))
		{
			version = newVersion;
			UserSpellWindow.updateGameVersion();
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