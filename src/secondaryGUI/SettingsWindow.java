package secondaryGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import userData.Settings;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingsWindow frame = new SettingsWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SettingsWindow() {
		setResizable(false);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 336, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWindowPositioningSetting = new JLabel("Window Positioning Behavior");
		lblWindowPositioningSetting.setBounds(10, 11, 144, 14);
		contentPane.add(lblWindowPositioningSetting);
		
		String[] centeringOptions = {"Do nothing on window call",
				"Only center windows when first appear",
				"Center windows whenever button is clicked"};
		
		JComboBox<Object> comboBox = new JComboBox<Object>(centeringOptions);
		comboBox.setSelectedIndex(Settings.getCenterFrames());
		comboBox.setBounds(10, 31, 310, 20);
		contentPane.add(comboBox);
		
		JButton btnSave = new JButton("Save and Quit");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.setCenterFrames(comboBox.getSelectedIndex());
				Settings.savePreferences();
				getThis().setVisible(false);
			}
		});
		btnSave.setBounds(176, 86, 144, 23);
		contentPane.add(btnSave);
	}
	
	public SettingsWindow getThis()
	{
		return this;
	}
}
