package alerts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guiPanels.SpellPanel;
import mainGUI.SpellBookLauncher;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * A DescriptionPopUp is a window which contains an editable textfield. 
 * Its use in this application is to serve as a display and editing window 
 * for spell descriptions in the DataBaseEditor
 * @author Daniel Campman
 * @version September 3, 2016
 */
@SuppressWarnings("serial")
public class DescriptionPopUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JTextArea textArea;
	private SpellPanel parent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DescriptionPopUp dialog = new DescriptionPopUp(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param parent The SpellPanel whose description this 
	 * popUp is displaying
	 */
	public DescriptionPopUp(SpellPanel parent) {
		//Gets the scaling factor from launcher class
		double scaleFactor = SpellBookLauncher.getScaleFactor();
		
		setTitle("Description");
		this.parent = parent; //sets parent
		setBounds((int)(100 * scaleFactor), //Sets size based on UI scaling value
				(int)(100 * scaleFactor),
				(int) (450 * scaleFactor), 
				(int) (300 * scaleFactor));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		//Scrollable pane for the textArea
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane);

		//textArea containing the text of the window
		textArea = new JTextArea(parent.getDescription());
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 
				(int) (13 * scaleFactor)));
		scrollPane.setViewportView(textArea);

		//Panel of buttons for confirming or canceling the edit
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		//this button confirms the edit and updates the SpellPanel with 
		//its new description 
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//update parent description
				parent.updateDescription(textArea.getText());
				//Closes this DescriptionPopUp
				disposeThis();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		//This button cancels the update and closes the window
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Closes this DescriptionPopUp
				disposeThis();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	/**
	 * Disposes of the window
	 */
	private void disposeThis() {
		dispose();
	}
	
	/**
	 * Refreshes the text shown for the description
	 */
	public void refresh() {
		textArea.setText(parent.getDescription());
	}

}
