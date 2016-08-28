package guiPanels;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Note extends JPanel {
	private JLabel lblTitle;
	private JLabel lblPreview;
	
	private String text;
	private String title;
	
	private ArrayList<ActionListener> observers = 
			new ArrayList<ActionListener>();
	private boolean mouseDown;
	
	/**
	 * Create the panel.
	 */
	public Note() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseDown = true;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (mouseDown) {
					notifyObservers();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mouseDown = false;
			}
		});
		setBackground(Color.WHITE);
		setLayout(new GridLayout(2, 0, 0, 0));
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(lblTitle);
		
		lblPreview = new JLabel("Preview");
		add(lblPreview);

		setTitle("New note");
		setText("");
	}
	
	/**
	 * Returns the title of the note
	 * @return {@code String} The title
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Sets the title of the note
	 * @param title The new title
	 */
	public void setTitle(String title)
	{
		this.title = title;
		lblTitle.setText(title);
	}
	
	/**
	 * Returns the text of the note
	 * @return {@code String} The text
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Sets the text of the note
	 * @param text The new text
	 */
	public void setText(String text)
	{
		this.text = text;
		lblPreview.setText(text);
	}
	
	/**
	 * Adds an action listener to detect when the 
	 * @param aL
	 */
	public void addActionListener(ActionListener aL)
	{
		observers.add(aL);
	}
	
	/**
	 * Sends a message out to all action listeners
	 */
	private void notifyObservers()
	{
		for (ActionListener aL : observers)
		{
			aL.actionPerformed(null);
		}
	}
	
	/**
	 * Provides a text representation of the Note
	 * @return {@code String} title + newLine + text
	 */
	@Override
	public String toString()
	{
		return title + "\n" + text;
	}
}
