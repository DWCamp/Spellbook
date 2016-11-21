package guiPanels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import alerts.DescriptionPopUp;
import files.Spell;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SpellPanel extends JPanel {
	private JTextField textFieldComp;
	private JTextField textFieldRange;
	private JTextField textFieldType;
	private JTextField textFieldTime;
	private JTextField textFieldName;
	private JTextField textFieldClasses;
	private JTextField textFieldDuration;
	private DescriptionPopUp popUp;
	
	private int level;
	private String description;

	/**
	 * Create the panel.
	 * @param spell The spell the panel represents
	 */
	public SpellPanel(Spell spell) {
		setLayout(new GridLayout(2, 0, 0, 0));
		level = spell.getLevel();
		popUp = new DescriptionPopUp(this);
		
		textFieldName = new JTextField(spell.getName());
		textFieldName.setToolTipText("The name of the spell");
		textFieldName.setColumns(10);
		add(textFieldName);
		
		String classes = "";
		for (String className : spell.getClasses()) {
			classes += "," + className;
		}
		if (classes.length() > 1) {
			textFieldClasses = new JTextField(classes.substring(1));
		} else {
			textFieldClasses = new JTextField();
		}
		textFieldClasses.setToolTipText("The classes that can use this spell, capitalized "
				+ "and separated by commas (i.e. 'Sorcerer, Wizard, Ranger')");
		textFieldClasses.setColumns(10);
		add(textFieldClasses);
		
		textFieldType = new JTextField(spell.getType());
		textFieldType.setToolTipText("The level and school of the spell (e.g. '1st-level evocation')");
		textFieldType.setColumns(10);
		add(textFieldType);
		
		textFieldTime = new JTextField(spell.getCastingTime());
		textFieldTime.setColumns(10);
		add(textFieldTime);
		
		textFieldRange = new JTextField(spell.getRange());
		add(textFieldRange);
		textFieldRange.setColumns(10);
		
		textFieldComp = new JTextField(spell.getComponents());
		add(textFieldComp);
		textFieldComp.setColumns(10);
		
		textFieldDuration = new JTextField(spell.getDuration());
		textFieldDuration.setColumns(10);
		add(textFieldDuration);
		
		description = spell.getDescription();
		
		JButton btnNewButton = new JButton("Description");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.refresh();
				popUp.setVisible(true);
			}
		});
		add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelf();
			}
		});
		add(btnDelete);

	}
	
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
	 * Sanitizes the class information on a panel
	 */
	public void sanitize()
	{
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
	private void deleteSelf()
	{
		this.getParent().remove(this);
	}
	
	/**
	 * Converts the contents of the pane into a spell
	 * @return {@code Spell}
	 */
	public Spell toSpell()
	{
		return new Spell(textFieldName.getText(),
				textFieldClasses.getText().split(","),
				textFieldType.getText(),
				level,
				textFieldTime.getText(),
				textFieldRange.getText(),
				textFieldComp.getText(),
				textFieldDuration.getText(),
				description);
	}

}
