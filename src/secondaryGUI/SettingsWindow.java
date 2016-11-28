package secondaryGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userData.Settings;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.border.LineBorder;

import mainGUI.UserSpellWindow;

import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox<Object> comboBoxCentering;
	private JComboBox<Object> comboBoxSize;
	
	private JPanel panelSpellBrowser;
	private JLabel lblSBrowser;
	private JCheckBox chckbxColor;
	
	private JLabel lblResizeValue;
	private JButton btnSave;
	private JButton btnSaveQuit;
	private JLabel lblWindowPositioningSetting;

	/**
	 * Create the frame.
	 */
	public SettingsWindow() {
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
				Settings.setCenterFrames(comboBoxCentering.getSelectedIndex());
				Settings.setScaleAdjustment(0.75 + (1 * comboBoxSize.getSelectedIndex()*0.25));
				Settings.setSBColor(chckbxColor.isSelected());
				Settings.savePreferences();
				refresh();
			}
		});
		contentPane.add(btnSave);
		
		btnSaveQuit = new JButton("Save & Close");
		btnSaveQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Settings.setCenterFrames(comboBoxCentering.getSelectedIndex());
				Settings.setScaleAdjustment(0.75 + (1 * comboBoxSize.getSelectedIndex()*0.25));
				Settings.setSBColor(chckbxColor.isSelected());
				Settings.savePreferences();
				setVisible(false);
			}
		});
		contentPane.add(btnSaveQuit);
		
		refresh();
		Point USW = UserSpellWindow.getWindowLocation();
		setLocation(USW.x + 30, USW.y + 30);
	}
	
	/**
	 * Refreshes the window to make sure it reflects current information
	 */
	public void refresh(){
		//Reset Values
		comboBoxCentering.setSelectedIndex(Settings.getCenterFrames());
		comboBoxSize.setSelectedIndex((int)(Settings.getScaleAdjustment()*4) - 3);
		chckbxColor.setSelected(Settings.getSBColor());
		
		//Get scale factor
		double scaleFactor = Settings.getResizeFactor();
		
		//Resize all components
		setBounds(getX(), getY(),
				(int)(336*scaleFactor), 
				(int)(242*scaleFactor) - (int)(37 * (scaleFactor-1)));
		
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
		
		lblSBrowser.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblSBrowser.setBounds((int)(10*scaleFactor), 
				(int)(113*scaleFactor), 
				(int)(250*scaleFactor), 
				(int)(14*scaleFactor));
		
		panelSpellBrowser.setBounds((int)(10*scaleFactor), 
				(int)(132*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(35*scaleFactor));
		
		chckbxColor.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSave.setBounds((int)(10*scaleFactor), 
				(int)(180*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(23*scaleFactor));
		
		btnSaveQuit.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSaveQuit.setBounds((int)(176*scaleFactor),
				(int)(180*scaleFactor),
				(int)(144*scaleFactor),
				(int)(23*scaleFactor));
	}
}
