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

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private JPanel contentPane;
	private JComboBox<Object> comboBoxCentering;
	private JComboBox<Object> comboBoxSize;
	
	private JLabel lblResizeValue;
	private JButton btnSave;
	private JLabel lblWindowPositioningSetting;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SettingsWindow frame = new SettingsWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SettingsWindow() {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		double scaleFactor = Settings.getResizeFactor();
		setBounds(100, 100, 
				(int)(336*scaleFactor), 
				(int)(180*scaleFactor) - (int)(27 * (scaleFactor-1)));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Image image = new ImageIcon("Icons/settings.PNG").getImage();
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblWindowPositioningSetting = new JLabel("Window Positioning Behavior");
		lblWindowPositioningSetting.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblWindowPositioningSetting.setBounds((int)(10*scaleFactor), 
				(int)(11*scaleFactor), 
				(int)(250*scaleFactor), 
				(int)(14*scaleFactor));
		contentPane.add(lblWindowPositioningSetting);
		
		String[] centeringOptions = {"Do nothing on window call",
				"Only center windows when first appear",
				"Center windows whenever button is clicked"};
		
		comboBoxCentering = new JComboBox<Object>(centeringOptions);
		comboBoxCentering.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxCentering.setSelectedIndex(Settings.getCenterFrames());
		comboBoxCentering.setBounds((int)(10*scaleFactor), 
				(int)(31*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		contentPane.add(comboBoxCentering);
		
		btnSave = new JButton("Save Preferences");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.setCenterFrames(comboBoxCentering.getSelectedIndex());
				Settings.setScaleAdjustment(0.75 + (1 * comboBoxSize.getSelectedIndex()*0.25));
				Settings.savePreferences();
				reset();
			}
		});
		btnSave.setBounds((int)(176*scaleFactor), 
				(int)(120*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(23*scaleFactor));
		contentPane.add(btnSave);
		
		lblResizeValue = new JLabel("Window Size");
		lblResizeValue.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		lblResizeValue.setBounds((int)(10*scaleFactor), 
				(int)(62*scaleFactor), 
				(int)(250*scaleFactor), 
				(int)(14*scaleFactor));
		contentPane.add(lblResizeValue);
		
		String[] sizeOptions = {"Very Small", "Small", "Default", "Large", "Very Large"};
		comboBoxSize = new JComboBox<Object>(sizeOptions);
		comboBoxSize.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		comboBoxSize.setSelectedIndex(0);
		comboBoxSize.setBounds((int)(10*scaleFactor), 
				(int)(82*scaleFactor), 
				(int)(310*scaleFactor), 
				(int)(20*scaleFactor));
		contentPane.add(comboBoxSize);
	}
	
	/**
	 * Refreshes the window to make sure it reflects current information
	 */
	public void reset(){
		comboBoxCentering.setSelectedIndex(Settings.getCenterFrames());
		switch ((int)(Settings.getScaleAdjustment()*4)) //Have to multiply scale by 4 to get ints
		{
			case 3:
				comboBoxSize.setSelectedIndex(0);
				break;
			case 4:
				comboBoxSize.setSelectedIndex(1);
				break;
			case 5:
				comboBoxSize.setSelectedIndex(2);
				break;
			case 6:
				comboBoxSize.setSelectedIndex(3);
				break;
			case 7:
				comboBoxSize.setSelectedIndex(4);
				break;
			default:
				comboBoxSize.setSelectedIndex(2);
		}
		
		double scaleFactor = Settings.getResizeFactor();
		
		setBounds(getX(), getY(),
				(int)(336*scaleFactor), 
				(int)(180*scaleFactor) - (int)(27 * (scaleFactor-1)));
		
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
		
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, (int)(11*scaleFactor)));
		btnSave.setBounds((int)(176*scaleFactor), 
				(int)(120*scaleFactor), 
				(int)(144*scaleFactor), 
				(int)(23*scaleFactor));
	}

}
