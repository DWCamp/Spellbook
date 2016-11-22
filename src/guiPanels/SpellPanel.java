package guiPanels;

import javax.swing.JTextField;

import alerts.DescriptionPopUp;
import helperClasses.Spell;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SpellPanel extends EditorSpellPanel {
	private JTextField textFieldComp;
	private JTextField textFieldRange;
	private JTextField textFieldType;
	private JTextField textFieldTime;
	private JTextField textFieldName;
	private JTextField textFieldClasses;
	private JTextField textFieldDuration;
	private DescriptionPopUp popUp;

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
		textFieldTime.setToolTipText("The time this spell takes to cast");
		textFieldTime.setColumns(10);
		add(textFieldTime);
		
		textFieldRange = new JTextField(spell.getRange());
		textFieldRange.setToolTipText("The range of the spell");
		textFieldRange.setColumns(10);
		add(textFieldRange);
		
		textFieldComp = new JTextField(spell.getComponents());
		textFieldComp.setToolTipText("The components needed to cast this spell");
		textFieldComp.setColumns(10);
		add(textFieldComp);
		
		textFieldDuration = new JTextField(spell.getDuration());
		textFieldDuration.setToolTipText("The duration of the spell");
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

	@Override
	void deleteSelf() {
		this.getParent().remove(this);
	}

}
