package guiPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import javax.swing.SwingConstants;

import mainGUI.LevelUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClassLevelPanel extends JPanel {
	
	private JButton btnPlus;
	private JButton btnMinus;
	private JTextField textField;
	private JLabel lblName;
	private int value;

	/**
	 * Create the panel.
	 */
	public ClassLevelPanel(LevelUp parent, String name) {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		lblName = new JLabel("  " + name);
		add(lblName);
		
		textField = new JTextField("0");
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		add(textField);
		textField.setColumns(10);
		
		setPreferredSize(new Dimension(250,25));
		
		btnPlus = new JButton("+");
		btnPlus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(btnPlus);
		
		btnMinus = new JButton("-");
		btnMinus.setEnabled(false);
		btnMinus.setFont(new Font("Tahoma", Font.PLAIN, 22));
		add(btnMinus);
																	//Action listeners
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.changeLevels(1);
				value -= 1;
				textField.setText(value + "");
				if (value <= 0)
				{
					btnMinus.setEnabled(false);
				}
			}
		});
		
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.changeLevels(-1);
				value += 1;
				textField.setText(value + "");
				if (!btnMinus.isEnabled())
				{
					btnMinus.setEnabled(true);
				}
			}
		});
	}
	
	/**
	 * Returns the integer value of the pane
	 * @return {@code int}
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Returns the name of the class associated with this panel
	 * @return {@code String}
	 */
	public String getClassName()
	{
		return lblName.getText().substring(2);
	}
	
	/**
	 * Set whether or not the plus button is available.
	 * @param state
	 */
	public void setPlusEnabled(boolean state)
	{
		btnPlus.setEnabled(state);
	}
	
	/**
	 * Resets the panel's attributes
	 */
	public void reset()
	{
		textField.setText("0");
		btnPlus.setEnabled(true);
		btnMinus.setEnabled(false);
	}
}
