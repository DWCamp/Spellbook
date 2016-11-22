package guiPanels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import alerts.DescriptionPopUp;
import helperClasses.Spell;

public abstract class EditorSpellPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected DescriptionPopUp popUp;
	private JTextField textFieldComp;
	private JTextField textFieldRange;
	private JTextField textFieldType;
	private JTextField textFieldTime;
	private JTextField textFieldName;
	private JTextField textFieldClasses;
	private JTextField textFieldDuration;
	
	protected int level;
	protected String description;
	
	/**
	 * Returns the name of the spell on this panel
	 * @return {@code String}
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Change's the spell's description
	 * @param newDesc
	 */
	public void updateDescription(String newDesc)
	{
		description = newDesc;
	}
	
	public void showDescription(){
		if (!popUp.isVisible()) {
			popUp.refresh();
			popUp.setVisible(true);
		}
		popUp.toFront();
	}
	
	/**
	 * Sets the "enabled" trait for all text fields on the panel
	 * @param state The new state of all textFields
	 */
	public void setTextFieldsEnabled(boolean state)
	{
		textFieldComp.setEnabled(state);
		textFieldRange.setEnabled(state);
		textFieldType.setEnabled(state);
		textFieldTime.setEnabled(state);
		textFieldName.setEnabled(state);
		textFieldClasses.setEnabled(state);
		textFieldDuration.setEnabled(state);
	}
	
	/**
	 * Sanitizes the information on a panel
	 */
	public void sanitize()
	{
		//Class
		String text = textFieldClasses.getText();
		String[] classes = text.split(",");
		for(int i = 0; i < classes.length; i++)
		{
			String element = classes[i];
			element = element.trim();
			if (element.length() > 0)
			{
				classes[i] = Character.toUpperCase(element.charAt(0)) 
					+ element.substring(1, element.length());
			}
		}
		text = "";
		for(String element : classes)
		{
			text += "," + element;
		}
		if (text.length() > 0)
		{
			textFieldClasses.setText(text.substring(1));
		}
	}
	
	/**
	 * Removes this panel from the parent container
	 */
	abstract void deleteSelf();
	
	/**
	 * Converts the contents of the pane into a spell
	 * @return {@code Spell}
	 */
	public abstract Spell toSpell();
}
