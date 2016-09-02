package alerts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guiPanels.SpellPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
	 */
	public DescriptionPopUp(SpellPanel parent) {
		setTitle("Description");
		this.parent = parent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				textArea = new JTextArea(parent.getDescription());
				textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parent.updateDescription(textArea.getText());
						getThis().dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getThis().dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Grabs <strong>this</strong> for closing
	 * @return this
	 */
	private DescriptionPopUp getThis()
	{
		return this;
	}
	
	/**
	 * Refreshes the text shown for the description
	 */
	public void refresh()
	{
		textArea.setText(parent.getDescription());
	}

}
