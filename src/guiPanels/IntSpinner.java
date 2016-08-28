package guiPanels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class IntSpinner extends JPanel {
	private JTextField textField;
	private int value;
	private int max;
	private int min;
	
	private int btnWidth = 20;
	private int btnHeight = 40;

	private ArrayList<ChangeListener> observers = 
			new ArrayList<ChangeListener>();
	
	/**
	 * Create the panel.
	 * @param minVal The minimum value for the spinner
	 * @param maxVal The maximum value for the spinner
	 * @param currValue the initial value of the spinner
	 * @throws Illegal Argument Exception if maxVal is small than minVal
	 */
	public IntSpinner(int minVal, int maxVal, int currVal) {
		setLayout(new BorderLayout(0, 0));
		
		if (maxVal < minVal) {
			throw new IllegalArgumentException("minVal cannot exceed maxVal");
		}

		if (currVal > maxVal) {
			currVal = maxVal;
		} else if (currVal < minVal) {
			currVal = minVal;
		}
		
		value = currVal;
		max = maxVal;
		min = minVal;
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0");
		add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPlus = new JButton("");
		btnPlus.setIcon(new ImageIcon(IntSpinner.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnPlus.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnPlus.setPreferredSize(new Dimension(btnWidth,btnHeight));
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (value < max) {
					value += 1;
					textField.setText(value + "");
				}
				notifyObservers();
			}
		});
		panel.add(btnPlus);
		
		JButton btnMinus = new JButton("");
		btnMinus.setIcon(new ImageIcon(IntSpinner.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnMinus.setPreferredSize(new Dimension(btnWidth,btnHeight));
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (value > min) {
					value -= 1;
					textField.setText(value + "");
				}
				notifyObservers();
			}
		});
		btnMinus.setFont(new Font("Tahoma", Font.BOLD, 5));
		panel.add(btnMinus);

	}
	
	/**
	 * Returns the value of the spinner
	 * @return {@code int}
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Resets the parameters of the spinner
	 * @param minVal The minimum value of the spinner v
	 * @param maxVal The maximum value of the spinner
	 * @param currVal The initial value of the spinner
	 * @throws IllegalArgumentException minVal cannot exceed maxVal
	 */
	public void reset(int minVal, int maxVal, int currVal)
	{
		if (maxVal < minVal) {
			throw new IllegalArgumentException("minVal cannot exceed maxVal");
		}

		if (currVal > maxVal) {
			currVal = maxVal;
		} else if (currVal < minVal) {
			currVal = minVal;
		}
		
		value = currVal;
		max = maxVal;
		min = minVal;
	}
	
	/**
	 * Sets whether the text of the spinner is editable
	 */
	public void setEditable(boolean state)
	{
		textField.setEditable(state);
	}
	
	/**
	 * Adds a change listener, I guess
	 * @param cL
	 */
	public void addChangeListener(ChangeListener cL)
	{
		observers.add(cL);;
	}
	
	/**
	 * Tells all the observers to do shit
	 */
	private void notifyObservers()
	{
		for (ChangeListener listener : observers)
		{
			listener.stateChanged(new ChangeEvent(this));
		}
	}

}
