package alerts;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import mainGUI.SpellBookLauncher;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class WordWrapPopUp extends JDialog {

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
	 */
	public WordWrapPopUp(String title, String text) {
		double scaleFactor = SpellBookLauncher.getScaleFactor();
		setTitle(title);
		setBounds(100,100,200,300);
		setMinimumSize(new Dimension(140,100));
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				textArea.setMargin(new Insets(3,5,3,3));
				scrollPane.setViewportView(textArea);
				textArea.setEditable(false);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * scaleFactor)));
				textArea.setText(text);
			}
		}
	}
	
	/**
	 * Sets the text in the dialog
	 * @param text New text
	 */
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	public void close()
	{
		this.dispose();
	}

}
