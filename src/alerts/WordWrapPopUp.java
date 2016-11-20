package alerts;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import userData.Settings;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * A resizable, non-editable pop-up which contains scrollable 
 * text which is set to word wrap by default
 * @author Daniel Campman
 * @version September 3, 2016
 */
@SuppressWarnings("serial")
public class WordWrapPopUp extends JDialog {

	//Contains the text
	private JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WordWrapPopUp dialog = new WordWrapPopUp("","");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param title The title of the window
	 * @param text The text in the body of the window
	 */
	public WordWrapPopUp(String title, String text) {
		//Gets the UI Scaling value from the launcher class
		//Default size is scaling independent. This value
		//is used to set the font size
		double scaleFactor = Settings.getResizeFactor();
		
		setTitle(title);
		setBounds(100, 100, 200, 300);
		setMinimumSize(new Dimension(140, 100));
		
		//sets the layout for the content pane
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Button pane for dismissing the window
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		//This button closes the window
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setMargin(new Insets(3, 5, 3, 3));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, (int) (13 * scaleFactor)));
		textArea.setText(text);
	}
	
	/**
	 * Sets the text in the dialog
	 * @param text New text
	 */
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	/**
	 * Disposes of the window
	 */
	private void close()
	{
		this.dispose();
	}

}
